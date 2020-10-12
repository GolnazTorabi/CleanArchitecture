package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.CharactersApi
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi):
    CharactersRepository {
    override fun getCharacters(): Single<List<ResponseCharacter>> {
        return charactersApi.getCharacters()
    }
}