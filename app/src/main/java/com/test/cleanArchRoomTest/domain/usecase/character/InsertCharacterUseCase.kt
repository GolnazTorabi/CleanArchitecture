package com.test.cleanArchRoomTest.domain.usecase.character

import android.content.ContentValues.TAG
import android.util.Log
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Maybe
import javax.inject.Inject

class InsertCharacterUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    fun insertCharacters(data: List<CharactersData>):Maybe<List<Long>>{
        return charactersRepository.insertAllCharacters(data)
    }
}