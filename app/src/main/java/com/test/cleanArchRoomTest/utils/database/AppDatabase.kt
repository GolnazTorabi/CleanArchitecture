package com.test.cleanArchRoomTest.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.cleanArchRoomTest.character.data.database.CharacterDao
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.episode.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.episode.data.database.EpisodeDao
import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData

@Database(entities = [CharactersData::class, EpisodeData::class, CharacterEpisodeCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
    abstract fun episodesDao(): EpisodeDao
    abstract fun characterEpisodesDao(): CharacterEpisodeDao
}