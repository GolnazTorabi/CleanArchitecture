package com.test.cleanArchRoomTest.data.repository

import android.annotation.SuppressLint
import com.test.cleanArchRoomTest.data.Api.CharactersApi
import com.test.cleanArchRoomTest.data.database.CharacterDao
import com.test.cleanArchRoomTest.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.data.database.EpisodeDao
import com.test.cleanArchRoomTest.data.mapper.character.CharacterToDbMapper
import com.test.cleanArchRoomTest.data.mapper.character.SpecificCharacterToDbMapper
import com.test.cleanArchRoomTest.data.mapper.episode.EpisodeToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.data.response.ResultsItem
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDao: CharacterDao,
    private val characterEpisodeDao: CharacterEpisodeDao,
    private val episodeDao: EpisodeDao
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
                                //TODO get episodes from char list
                                //insertEpisodes()
                                //TODO insert episode
                                // TODO insertCharacterEpisode(server.results).flatMap
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
    private fun insertEpisodes(data: List<ResultsItemEpisode?>): Maybe<List<Long>> {
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
                    list.add(CharacterEpisodeCrossRef(it.id!!, character.id!!))
                }
            }
        }
        return characterEpisodeDao.insertCharacterEpisodes(list)

    }

    override fun getCharacters(): Single<ResponseCharacter> {
        return charactersApi.getCharacters()
    }

    private fun insert(data: List<ResultsItem>): Maybe<List<Long>> {
        val list = CharacterToDbMapper().reverseMap(data)
        return characterDao.insertCharacters(list)
    }
    private fun insertUser(data:ResponseSpecificCharacter):Maybe<Long>{
        val value = SpecificCharacterToDbMapper().reverseMap(data)
        return value?.let { characterDao.insertCharacter(it) }!!
    }

    override fun getSpecificCharacter(id: String): Single<ResponseSpecificCharacter> {
        return charactersApi.getSpecificCharacter(id)

    }

    override fun getSpecificCharacterFromDB(id: String): Maybe<CharactersData> {
        return characterDao.getSpecificCharacters(id).flatMap {
          if(it.name.isNullOrEmpty()){
              getSpecificCharacter(id).toMaybe().flatMap { server ->
                  if(server.name.isNullOrEmpty()){
                      Maybe.just(CharactersData())
                  } else{
                      insertUser(server).flatMap {
                          getSpecificCharacterFromDB(id)
                      }
                  }

              }
          } else{
              Maybe.just(it)
          }
        }
    }
}