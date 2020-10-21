package com.test.cleanArchRoomTest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.cleanArchRoomTest.domain.model.*

@Database(entities = [CharactersData::class,EpisodeData::class,CharacterEpisodeCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
    abstract fun episodesDao(): EpisodeDao
    abstract fun characterEpisodesDao(): CharacterEpisodeDao
}