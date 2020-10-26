package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.database.CharacterEpisodeDao
import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
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