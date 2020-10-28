package com.test.cleanArchRoomTest.episode.domain.repository

import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import io.reactivex.Maybe

interface CharacterEpisodeRepository {
    //fun getCharacterEpisode(): List<CharacterEpisodeCrossRef>
    fun insertCharacterEpisode(data: CharacterEpisodeCrossRef): Maybe<Long>
}