package com.test.cleanArchRoomTest.character.data.mapper.character

import com.test.cleanArchRoomTest.utils.database.Mapper
import com.test.cleanArchRoomTest.character.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.character.domain.model.CharactersData

class SpecificCharacterToDbMapper : Mapper<CharactersData, ResponseSpecificCharacter>() {
    override fun map(value: CharactersData?): ResponseSpecificCharacter {
        throw UnsupportedOperationException()
    }

    override fun reverseMap(value: ResponseSpecificCharacter?): CharactersData? {
        return if (value == null) {
            null
        } else {
            CharactersData(
                value.id,
                value.image,
                value.gender,
                value.url,
                value.origin,
                value.name,
                value.location
            )
        }
    }
}