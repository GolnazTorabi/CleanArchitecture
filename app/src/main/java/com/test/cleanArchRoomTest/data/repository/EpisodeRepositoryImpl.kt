package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.EpisodeApi
import com.test.cleanArchRoomTest.data.database.CharacterDao
import com.test.cleanArchRoomTest.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.data.database.EpisodeDao
import com.test.cleanArchRoomTest.data.mapper.episode.EpisodeToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.data.response.ResultsItem
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val episodeDao: EpisodeDao,
    private val characterDao: CharacterDao,
    private val characterEpisodeDao: CharacterEpisodeDao
) :
    EpisodeRepository {

    override fun getEpisodes(): Single<ResponseEpisodes> {
        return episodeApi.getEpisodes()
    }

    override fun getEpisodesFromDB(hasNetwork: Boolean): Maybe<List<EpisodeData>?> {
        if (hasNetwork) {
            episodeDao.deleteAllEpisodes()
            return episodeDao.getAllEpisodes().flatMap { data ->
                if (data.isEmpty()) {
                    getEpisodes().toMaybe().flatMap { server ->
                        if (server.results.isNullOrEmpty()) {
                            Maybe.just(ArrayList())
                        } else {
                            insertEpisodes(server.results.filterNotNull()).flatMap {

                                //insertCharacterEpisode(server.results)
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

    private fun insertEpisodes(data: List<ResultsItemEpisode?>): Maybe<List<Long>> {
        val list = EpisodeToDbMapper().reverseMap(data)
        return episodeDao.insertEpisodes(list)
    }

    private fun insertCharacterEpisode(data: List<ResultsItem?>): Maybe<List<Long>> {
        val list: MutableList<CharacterEpisodeCrossRef> = mutableListOf()
        data.forEach { character ->
            character?.episode?.forEach { episode ->
                episode?.let {
                    list.add(CharacterEpisodeCrossRef(it.id!!, character.id!!))
                }
            }
        }
        return characterEpisodeDao.insertCharacterEpisodes(list)

    }
}