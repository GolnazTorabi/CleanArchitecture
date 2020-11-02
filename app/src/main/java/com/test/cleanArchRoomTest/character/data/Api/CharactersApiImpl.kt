package com.test.cleanArchRoomTest.character.data.Api

import com.test.cleanArchRoomTest.character.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.character.data.response.ResponseSpecificCharacter
import io.reactivex.Single
import javax.inject.Inject

class CharactersApiImpl @Inject constructor(private val apiInterface: CharacterApi) {
    fun getCharacters(): Single<ResponseCharacter> {
        return apiInterface.getCharacters()
    }
    fun getSpecificCharacter(id:String):Single<ResponseSpecificCharacter>{
        return apiInterface.getSpecificCharacter(id)
    }
}