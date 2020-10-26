package com.test.cleanArchRoomTest.domain.usecase.episode

import com.test.cleanArchRoomTest.data.mapper.episode.EpisodeToDbMapper
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(private val episodeRepository: EpisodeRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val episode: EpisodeData) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getEpisodes(id: String, hasNetwork: Boolean): Observable<Result> {
        return if (!hasNetwork) {
            return episodeRepository.getEpisodeFromDB(id)
                .toObservable()
                .map {
                    Result.Success(it) as Result
                }
                .onErrorReturn { Result.Failure(it) }
                .startWith(Result.Loading)
        } else {
            episodeRepository.deleteAllEpisodes()
            episodeRepository.getEpisode(id).toObservable().map {
                val data = EpisodeToDbMapper().reverseMap(it)
                Result.Success(data!!) as Result
            }
                .onErrorReturn { Result.Failure(it) }
                .startWith(Result.Loading)
        }
    }
}