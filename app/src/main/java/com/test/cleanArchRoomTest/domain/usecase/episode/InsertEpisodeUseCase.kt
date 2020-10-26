package com.test.cleanArchRoomTest.domain.usecase.episode

import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertEpisodeUseCase @Inject constructor(private val episodeRepository: EpisodeRepository) {
    fun insertEpisode(data: EpisodeData): Maybe<Long> {
        return episodeRepository.insertEpisode(data)
    }
}