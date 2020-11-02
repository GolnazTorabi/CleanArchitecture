package com.test.cleanArchRoomTest.episode.data.Api

import com.test.cleanArchRoomTest.episode.data.response.ResultsItemEpisode
import io.reactivex.Single
import javax.inject.Inject

class EpisodeApiImpl @Inject constructor(private val apiInterface: EpisodeApi) {
    fun getEpisode(id:String): Single<ResultsItemEpisode>{
        return apiInterface.getEpisode(id)
    }
}