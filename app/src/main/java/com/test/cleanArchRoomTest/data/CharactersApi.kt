package com.test.cleanArchRoomTest.data

import com.test.cleanArchRoomTest.data.response.ResponseCharacter
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersApi @Inject constructor(private val apiInterface: ApiInterface) {
    fun getCharacters():Single<List<ResponseCharacter>>{
        return apiInterface.getCharacters()
    }
}