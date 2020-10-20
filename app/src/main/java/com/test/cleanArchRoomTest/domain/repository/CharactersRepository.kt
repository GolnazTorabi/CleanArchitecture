package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.domain.model.CharactersData
import io.reactivex.Maybe
import io.reactivex.Single

interface CharactersRepository {

    fun getCharactersFromDb(): Maybe<List<CharactersData>>
    fun getCharacters(): Single<ResponseCharacter>
    fun getSpecificCharacter(id: String): Single<ResponseSpecificCharacter>

}