package com.test.cleanArchRoomTest.character.data.repository

import com.test.cleanArchRoomTest.character.data.Api.CharactersApiImpl
import com.test.cleanArchRoomTest.character.data.database.CharacterDao
import com.test.cleanArchRoomTest.character.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.character.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.character.domain.repository.CharactersRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApiImpl,
    private val characterDao: CharacterDao
) : CharactersRepository {
    override fun getCharactersFromDb(): Maybe<List<CharactersData>?> {
        return characterDao.getAllCharacters()
    }

/*    private fun insertEpisodes(data: List<ResultsItemEpisode?>): Maybe<List<Long>> {
        val list = EpisodeToDbMapper().reverseMap(data)
        data.forEach { data ->
            list.addAll(list)
        }
        return episodeDao.insertEpisodes(list)
    }
    private fun insertCharacterEpisode(data: List<ResultsItem?>): Maybe<List<Long>> {
        val list: MutableList<CharacterEpisodeCrossRef> = mutableListOf()
        data.forEach { character ->
            character?.episode?.forEach { episode ->
                episode?.let {
                    //list.add(CharacterEpisodeCrossRef(it.id!!, character.id!!))
                }
            }
        }
        return characterEpisodeDao.insertCharacterEpisodes(list)

    }*/

    override fun getCharacters(): Single<ResponseCharacter> {
        return charactersApi.getCharacters()
    }

    /* private fun insert(data: List<ResultsItem>): Maybe<List<Long>> {
         val list = CharacterToDbMapper().reverseMap(data)
         return characterDao.insertCharacters(list)
     }*/


    override fun deleteAllCharacters(): Single<Int> {
        return characterDao.deleteAllCharacters()
    }

    override fun getCharacterEpisode(id: String): Maybe<List<String>> {
        return characterDao.getCharacterEpisodes(id)
    }

    override fun getSpecificCharacter(id: String): Single<ResponseSpecificCharacter> {
        return charactersApi.getSpecificCharacter(id)

    }

    override fun getSpecificCharacterFromDB(id: String): Maybe<CharactersData> {
        return characterDao.getSpecificCharacters(id)
    }

    override fun insertAllCharacters(data: List<CharactersData>): Maybe<List<Long>> {
        return characterDao.insertCharacters(data)
    }
}