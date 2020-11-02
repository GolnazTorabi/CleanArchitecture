package com.test.cleanArchRoomTest.utils.database

import android.content.Context
import androidx.room.Room
import com.test.cleanArchRoomTest.character.data.database.CharacterDao
import com.test.cleanArchRoomTest.episode.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.episode.data.database.EpisodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "CHARACTERS-DATA.db"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideCharacterDao(database: AppDatabase): CharacterDao {
        return database.charactersDao()
    }
    @Provides
    fun provideEpisodeDao(database: AppDatabase): EpisodeDao {
        return database.episodesDao()
    }

    @Provides
    fun provideCharacterEpisodeDao(database: AppDatabase): CharacterEpisodeDao {
        return database.characterEpisodesDao()
    }


}