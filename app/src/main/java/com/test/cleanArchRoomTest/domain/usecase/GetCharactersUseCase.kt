package com.test.cleanArchRoomTest.domain.usecase


import com.test.cleanArchRoomTest.data.mapper.character.CharacterToDbMapper
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Failure
import com.test.cleanArchRoomTest.domain.usecase.GetCharactersUseCase.Result.Success
import io.reactivex.Observable
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val responseCharacter: List<CharactersData>) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun getCharacters(hasNetwork: Boolean): Observable<Result> {
        return if (!hasNetwork) {
            charactersRepository.getCharactersFromDb()
                .toObservable()
                .map { Success(it) as Result }
                .onErrorReturn { Failure(it) }
                .startWith(Result.Loading)
        } else {
            charactersRepository.deleteAllCharacters()
            charactersRepository.getCharacters().toObservable().map {
                val data = CharacterToDbMapper().reverseMap(it.results)
                Success(data) as Result
            }
                .onErrorReturn { Failure(it) }
                .startWith(Result.Loading)
        }
    }
}



