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
    fun getPlaylistsWithSongs(): List<CharactersWithEpisode>

    @Transaction
    @Query("SELECT * FROM Episode")
    fun getSongsWithPlaylists(): List<EpisodesWithCharacters>

}