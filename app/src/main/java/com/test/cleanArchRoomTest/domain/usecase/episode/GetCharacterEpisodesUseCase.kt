package com.test.cleanArchRoomTest.domain.usecase.episode

import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCharacterEpisodesUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    sealed class Result {
        data class Success(val responseCharacter: List<String>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getEpisodes(id: String): Observable<Result> {
        return charactersRepository.getCharacterEpisode(id)
            .toObservable()
            .map {
                Result.Success(it) as Result
            }
            .onErrorReturn { Result.Failure(it) }

    }

}