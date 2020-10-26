package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.data.response.ResponseSpecificCharacter
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import io.reactivex.Maybe
import io.reactivex.Single

interface EpisodeRepository {
    fun getEpisode(id:String):Single<ResultsItemEpisode>
    fun getEpisodeFromDB(id:String):Maybe<EpisodeData>
    fun insertEpisode(data: EpisodeData):Maybe<Long>
    fun deleteAllEpisodes():Single<Int>

}