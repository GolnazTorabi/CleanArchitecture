package com.test.cleanArchRoomTest.domain.usecase


import android.util.Log
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Failure
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Success

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    sealed class Result{
        object Loading : Result()
        data class Success(val responseCharacter: List<ResponseCharacter>): Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getCharacters(): Observable<Result> {
        return charactersRepository.getCharacters()
            .toObservable()
            .map<Result>{Success(it)}
            .onErrorReturn{Failure(it)}
            /*.startWith{Result}*/



    }
}



