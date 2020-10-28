package com.test.cleanArchRoomTest.episode.domain.usecase.episode

import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData
import com.test.cleanArchRoomTest.episode.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertEpisodeUseCase @Inject constructor(private val episodeRepository: EpisodeRepository) {
    fun insertEpisode(data: EpisodeData): Maybe<Long> {
        return episodeRepository.insertEpisode(data)
    }
}