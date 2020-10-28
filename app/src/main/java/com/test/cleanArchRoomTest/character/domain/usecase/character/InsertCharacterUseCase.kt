package com.test.cleanArchRoomTest.character.domain.usecase.character

import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.character.domain.repository.CharactersRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertCharacterUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    fun insertCharacters(data: List<CharactersData>):Maybe<List<Long>>{
        return charactersRepository.insertAllCharacters(data)
    }
}