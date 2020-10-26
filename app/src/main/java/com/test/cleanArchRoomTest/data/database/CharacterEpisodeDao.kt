package com.test.cleanArchRoomTest.data.database

import androidx.room.*
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodesWithCharacters
import io.reactivex.Maybe

@Dao
interface CharacterEpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterEpisode(value: CharacterEpisodeCrossRef): Maybe<Long>

   /* @Transaction
    @Query("SELECT * FROM Characters")
    fun getCharactersWithEpisodes(): List<CharactersWithEpisode>

    @Transaction
    @Query("SELECT * FROM Episode")
    fun getEpisodesWithCharacters(): List<EpisodesWithCharacters>

    @Transaction
    @Query("SELECT * FROM Characters Where characterId =:id")
    fun getCharacterWithEpisodes(id: String): List<CharactersWithEpisode>

    @Transaction
    @Query("SELECT * FROM Episode Where episodeId =:id")
    fun getEpisodeWithCharacter(id: String): List<EpisodesWithCharacters>*/
}