package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.EpisodeApi
import com.test.cleanArchRoomTest.data.database.EpisodeDao
import com.test.cleanArchRoomTest.data.mapper.episode.EpisodeToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val episodeDao: EpisodeDao
) :
    EpisodeRepository {
    override fun getEpisodes(): Single<ResponseEpisodes> {
        return episodeApi.getEpisodes()
    }

    override fun getEpisodesFromDB(hasNetwork: Boolean): Maybe<List<EpisodeData>?> {
        if (hasNetwork) {
            episodeDao.deleteAllEpisodes()
            return episodeDao.getAllEpisodes().flatMap {data ->
                if(data.isEmpty()){
                    getEpisodes().toMaybe().flatMap {server ->
                        if(server.results.isNullOrEmpty()){
                            Maybe.just(ArrayList())
                        } else {
                            insertEpisodes(server.results.filterNotNull()).flatMap {
                                getEpisodesFromDB(false)
                            }
                        }
                    }
                } else {
                    Maybe.just(data)
                }
            }
        } else {
            return episodeDao.getAllEpisodes()
        }
    }
    private fun insertEpisodes(data:List<ResultsItemEpisode?>):Maybe<List<Long>>{
        val list = EpisodeToDbMapper().reverseMap(data)
        return episodeDao.insertEpisodes(list)
    }
}