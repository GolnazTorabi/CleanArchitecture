package com.test.cleanArchRoomTest.domain.usecase.episode

import com.test.cleanArchRoomTest.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertCharacterEpisodeCrossUseCase @Inject constructor(private val characterEpisodeRepository: CharacterEpisodeRepository) {

    fun insertCharacterEpisodeCross(data: CharacterEpisodeCrossRef): Maybe<Long> {
        return characterEpisodeRepository.insertCharacterEpisode(data)
    }

}