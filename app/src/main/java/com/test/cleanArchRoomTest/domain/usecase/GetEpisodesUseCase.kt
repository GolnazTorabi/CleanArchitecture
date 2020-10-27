package com.test.cleanArchRoomTest.domain.usecase

import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val episodeRepository: EpisodeRepository) {

    sealed class ResultEpisode{
        object LoadingEpisode : ResultEpisode()
        data class SuccessEpisode(val responseEpisode: List<EpisodeData>): ResultEpisode()
        data class FailureEpisode(val throwable: Throwable) : ResultEpisode()
    }

    /*fun getEpisodes(): Observable<ResultEpisode> {
        return episodeRepository.getEpisodesFromDB()
            .toObservable()
            .map { ResultEpisode.SuccessEpisode(it) as ResultEpisode }
            .onErrorReturn { ResultEpisode.FailureEpisode(it) }
            .startWith(ResultEpisode.LoadingEpisode)

    }*/
}



