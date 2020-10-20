package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Single<ResponseCharacter>
    fun getSpecificCharacter(id:String): Single<ResponseSpecificCharacter>
}