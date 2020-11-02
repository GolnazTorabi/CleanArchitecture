package com.test.cleanArchRoomTest.episode.data.repository

import com.test.cleanArchRoomTest.episode.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.episode.domain.repository.CharacterEpisodeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class CharacterEpisodeRepositoryImpl @Inject constructor(
    private val characterEpisodeDao: CharacterEpisodeDao
) : CharacterEpisodeRepository {
    /*override fun getCharacterEpisode(): List<CharacterEpisodeCrossRef> {
        //return characterEpisodeDao.getCharactersWithEpisodes()
        return
    }*/

    override fun insertCharacterEpisode(data: CharacterEpisodeCrossRef): Maybe<Long> {
        return characterEpisodeDao.insertCharacterEpisode(data)
    }
}