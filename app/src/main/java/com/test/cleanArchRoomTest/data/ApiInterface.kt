package com.test.cleanArchRoomTest.data

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("api/character")
    fun getCharacters(): Single<ResponseCharacter>

    @GET("api/character/{id}")
    fun getSpecificCharacter(@Path("id") id: String): Single<ResponseSpecificCharacter>

    @GET("api/episode/")
    fun getEpisodes():Single<ResponseEpisodes>
}