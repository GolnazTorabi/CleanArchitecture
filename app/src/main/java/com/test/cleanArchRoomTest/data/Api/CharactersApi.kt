package com.test.cleanArchRoomTest.data.Api

import com.test.cleanArchRoomTest.data.ApiInterface
import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import io.reactivex.Single
import javax.inject.Inject

class CharactersApi @Inject constructor(private val apiInterface: ApiInterface) {
    fun getCharacters(): Single<ResponseCharacter> {
        return apiInterface.getCharacters()
    }
    fun getSpecificCharacter(id:String):Single<ResponseSpecificCharacter>{
        return apiInterface.getSpecificCharacter(id)
    }
}