package com.test.cleanArchRoomTest.character.domain.repository

import com.test.cleanArchRoomTest.character.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.character.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import io.reactivex.Maybe
import io.reactivex.Single

interface CharactersRepository {

    fun getCharactersFromDb(): Maybe<List<CharactersData>?>
    fun getCharacters(): Single<ResponseCharacter>
    fun getSpecificCharacter(id: String): Single<ResponseSpecificCharacter>
    fun getSpecificCharacterFromDB(id: String): Maybe<CharactersData>
    fun insertAllCharacters(data: List<CharactersData>):Maybe<List<Long>>
    fun deleteAllCharacters():Single<Int>
    fun getCharacterEpisode(id: String):Maybe<List<String>>

}