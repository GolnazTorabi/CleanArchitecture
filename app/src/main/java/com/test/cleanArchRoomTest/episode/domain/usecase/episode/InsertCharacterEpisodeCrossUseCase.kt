package com.test.cleanArchRoomTest.episode.domain.usecase.episode

import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.episode.domain.repository.CharacterEpisodeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertCharacterEpisodeCrossUseCase @Inject constructor(private val characterEpisodeRepository: CharacterEpisodeRepository) {

    fun insertCharacterEpisodeCross(data: CharacterEpisodeCrossRef): Maybe<Long> {
        return characterEpisodeRepository.insertCharacterEpisode(data)
    }

}