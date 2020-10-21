package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
import javax.inject.Inject

class CharacterEpisodeRepositoryImpl @Inject constructor(
    private val characterEpisodeDao: CharacterEpisodeDao
) : CharacterEpisodeRepository {
    override fun getCharacterEpisode(): List<CharactersWithEpisode> {
        return characterEpisodeDao.getCharactersWithEpisodes()
    }
}