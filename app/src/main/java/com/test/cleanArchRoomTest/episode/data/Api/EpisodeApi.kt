package com.test.cleanArchRoomTest.episode.data.Api

import com.test.cleanArchRoomTest.episode.data.response.ResultsItemEpisode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("api/episode/{id}")
    fun getEpisode(@Path("id")id:String): Single<ResultsItemEpisode>
}