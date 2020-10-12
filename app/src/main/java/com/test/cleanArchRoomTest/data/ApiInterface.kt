package com.test.cleanArchRoomTest.data

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/character")
    fun getCharacters():Single<List<ResponseCharacter>>
}