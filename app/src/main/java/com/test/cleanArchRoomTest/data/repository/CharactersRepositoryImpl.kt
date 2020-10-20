package com.test.cleanArchRoomTest.data.repository

import android.annotation.SuppressLint
import com.test.cleanArchRoomTest.data.CharactersApi
import com.test.cleanArchRoomTest.data.database.CharacterDao
import com.test.cleanArchRoomTest.data.mapper.character.CharacterToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.data.response.ResultsItem
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDao: CharacterDao
) : CharactersRepository {
    @SuppressLint("CheckResult")
    override fun getCharactersFromDb(hasNetwork: Boolean): Maybe<List<CharactersData>?> {
        //check if has network delete previous data and get new one else get from Db
        if (hasNetwork) {
            characterDao.deleteAllCharacters()
            return characterDao.getAllCharacters().flatMap {
                if (it.isEmpty()) {
                    getCharacters().toMaybe().flatMap { server ->
                        if (server.results.isNullOrEmpty()) {
                            Maybe.just(ArrayList())
                        } else {
                            insert(server.results.filterNotNull()).flatMap {
                                getCharactersFromDb(false)
                            }
                        }
                    }
                } else {
                    Maybe.just(it)
                }
            }
        } else {
            return characterDao.getAllCharacters()
        }

    }

    override fun getCharacters(): Single<ResponseCharacter> {
        return charactersApi.getCharacters()
    }

    private fun insert(data: List<ResultsItem>): Maybe<List<Long>> {
        val list = CharacterToDbMapper().reverseMap(data)
        return characterDao.insertCharacters(list)
    }

    override fun getSpecificCharacter(id: String): Single<ResponseSpecificCharacter> {
        return charactersApi.getSpecificCharacter(id)

    }
}