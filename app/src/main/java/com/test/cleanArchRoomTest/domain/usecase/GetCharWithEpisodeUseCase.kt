package com.test.cleanArchRoomTest.domain.usecase

import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import com.test.cleanArchRoomTest.domain.repository.CharacterEpisodeRepository
import com.test.cleanArchRoomTest.domain.repository.CharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCharWithEpisodeUseCase @Inject constructor(private val characterEpisodeRepository: CharacterEpisodeRepository) {
    fun getCharacters(): List<CharactersWithEpisode> {
        return characterEpisodeRepository.getCharacterEpisode()



    }
}
