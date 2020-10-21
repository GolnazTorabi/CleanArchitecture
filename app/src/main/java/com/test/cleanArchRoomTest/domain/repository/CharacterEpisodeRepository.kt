package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import io.reactivex.Maybe
import io.reactivex.Single

interface CharacterEpisodeRepository {
    fun getCharacterEpisode():List<CharactersWithEpisode>
}