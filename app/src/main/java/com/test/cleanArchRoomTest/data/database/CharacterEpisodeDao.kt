package com.test.cleanArchRoomTest.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodesWithCharacters

@Dao
interface CharacterEpisodeDao {
    @Transaction
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
    fun getEpisodeWithCharacter(id: String): List<EpisodesWithCharacters>
}