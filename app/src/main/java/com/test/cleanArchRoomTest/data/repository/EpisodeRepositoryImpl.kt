package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.EpisodeApi
import com.test.cleanArchRoomTest.data.database.EpisodeDao
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
) : EpisodeRepository {

    override fun getEpisode(id: String): Single<ResultsItemEpisode> {
        return episodeApi.getEpisode(id)
    }

    override fun getEpisodeFromDB(id: String): Maybe<EpisodeData> {
        return episodeDao.getSpecificEpisodes(id)
    }

    override fun insertEpisode(data: EpisodeData): Maybe<Long> {
        return episodeDao.insertEpisode(data)
    }

    override fun deleteAllEpisodes(): Single<Int> {
        return episodeDao.deleteAllEpisodes()
    }

}