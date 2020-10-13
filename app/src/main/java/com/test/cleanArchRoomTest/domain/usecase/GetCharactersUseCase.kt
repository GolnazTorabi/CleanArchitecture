package com.test.cleanArchRoomTest.domain.usecase


import android.util.Log
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import javax.inject.Inject
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Failure
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Success
import io.reactivex.Observable

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    sealed class Result{
        object Loading : Result()
        data class Success(val responseCharacter: List<ResponseCharacter>): Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getCharacters(): Observable<Result> {
        return charactersRepository.getCharacters()
            .toObservable()
            .map { Success(it) as Result }
            .onErrorReturn { Failure(it) }
            .startWith(Result.Loading)

    }
}



