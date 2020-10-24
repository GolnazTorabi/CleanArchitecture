package com.test.cleanArchRoomTest.data.mapper.character

import com.test.cleanArchRoomTest.data.mapper.Mapper
import com.test.cleanArchRoomTest.data.response.ResultsItem
import com.test.cleanArchRoomTest.domain.model.CharactersData

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
                value.status
            )
        }
    }
}