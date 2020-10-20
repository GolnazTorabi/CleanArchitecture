package com.test.cleanArchRoomTest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.cleanArchRoomTest.domain.model.CharactersData

@Database(entities = [CharactersData::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharacterDao
}