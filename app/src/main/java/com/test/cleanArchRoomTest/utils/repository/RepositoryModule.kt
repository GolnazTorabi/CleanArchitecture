package com.test.cleanArchRoomTest.utils.repository

import com.test.cleanArchRoomTest.character.data.repository.CharactersRepositoryImpl
import com.test.cleanArchRoomTest.character.domain.repository.CharactersRepository
import com.test.cleanArchRoomTest.episode.data.repository.CharacterEpisodeRepositoryImpl
import com.test.cleanArchRoomTest.episode.data.repository.EpisodeRepositoryImpl
import com.test.cleanArchRoomTest.episode.domain.repository.CharacterEpisodeRepository
import com.test.cleanArchRoomTest.episode.domain.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideEpisodeRepository(repo: EpisodeRepositoryImpl): EpisodeRepository = repo

    @Provides
    fun provideCharacterEpisodeRepository(repo: CharacterEpisodeRepositoryImpl): CharacterEpisodeRepository =
        repo
    @Provides
    fun provideCharactersRepository(repo: CharactersRepositoryImpl): CharactersRepository = repo

}