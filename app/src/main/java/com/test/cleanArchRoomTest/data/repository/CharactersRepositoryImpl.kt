package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.CharactersApi
import com.test.cleanArchRoomTest.data.database.CharacterDao
import com.test.cleanArchRoomTest.data.mapper.character.CharacterToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResultsItem
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDao: CharacterDao
) :
    CharactersRepository {
    override fun getCharactersFromDb(): Maybe<List<CharactersData>> {
        return characterDao.getAllCharacters().flatMap {
            if (it.isEmpty()) {
                getCharacters().toMaybe().flatMap { server ->
                    if (server.results.isNullOrEmpty()) {
                        Maybe.just(ArrayList())
                    } else {
                        insert(server.results.filterNotNull()).flatMap {
                            getCharactersFromDb()
                        }
                    }
                }
            } else {
                Maybe.just(it)
            }
        }

    }

    override fun getCharacters(): Single<ResponseCharacter> {
        return charactersApi.getCharacters()
    }

    private fun insert(data: List<ResultsItem>): Maybe<List<Long>> {
        val list = CharacterToDbMapper().reverseMap(data)
        return characterDao.insertCharacters(list)
    }
}