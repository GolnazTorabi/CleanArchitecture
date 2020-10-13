package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import io.reactivex.Single

interface CharactersRepository {

    fun getCharacters(): Single<List<ResponseCharacter>>
}