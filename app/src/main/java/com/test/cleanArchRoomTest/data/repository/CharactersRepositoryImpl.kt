package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.CharactersApi
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepositoryImpl @Inject constructor(private val charactersApi: CharactersApi):
    CharactersRepository {
    override fun getCharacters(): Single<ResponseCharacter> {
        return charactersApi.getCharacters()
    }
}