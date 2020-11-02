package com.test.cleanArchRoomTest.character.data.Api

import com.test.cleanArchRoomTest.character.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.character.data.response.ResponseSpecificCharacter
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("api/character")
    fun getCharacters(): Single<ResponseCharacter>

    @GET("api/character/{id}")
    fun getSpecificCharacter(@Path("id") id: String): Single<ResponseSpecificCharacter>

}