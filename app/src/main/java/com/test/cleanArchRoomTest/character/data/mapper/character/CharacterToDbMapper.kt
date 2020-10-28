package com.test.cleanArchRoomTest.character.data.mapper.character

import com.test.cleanArchRoomTest.utils.database.Mapper
import com.test.cleanArchRoomTest.character.data.response.ResultsItem
import com.test.cleanArchRoomTest.character.domain.model.CharactersData

class CharacterToDbMapper : Mapper<CharactersData, ResultsItem>() {
    override fun map(value: CharactersData?): ResultsItem {
        throw UnsupportedOperationException()
    }

    override fun reverseMap(value: ResultsItem?): CharactersData? {
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
                value.location,
                value.status,
                value.episode
            )
        }
    }
}