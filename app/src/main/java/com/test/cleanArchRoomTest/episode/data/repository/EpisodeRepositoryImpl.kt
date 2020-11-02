package com.test.cleanArchRoomTest.episode.data.repository

import com.test.cleanArchRoomTest.episode.data.Api.EpisodeApiImpl
import com.test.cleanArchRoomTest.episode.data.database.EpisodeDao
import com.test.cleanArchRoomTest.episode.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData
import com.test.cleanArchRoomTest.episode.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApiImpl,
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