package com.test.cleanArchRoomTest.data.Api

import com.test.cleanArchRoomTest.data.ApiInterface
import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class EpisodeApi @Inject constructor(private val apiInterface: ApiInterface) {
    fun getEpisodes(): Single<ResponseEpisodes>{
        return apiInterface.getEpisodes()
    }
}