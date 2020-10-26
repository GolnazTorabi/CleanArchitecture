package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import io.reactivex.Maybe

interface CharacterEpisodeRepository {
    //fun getCharacterEpisode(): List<CharacterEpisodeCrossRef>
    fun insertCharacterEpisode(data: CharacterEpisodeCrossRef): Maybe<Long>
}