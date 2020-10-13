package com.test.cleanArchRoomTest.data

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/character")
    fun getCharacters(): Single<ResponseCharacter>
}