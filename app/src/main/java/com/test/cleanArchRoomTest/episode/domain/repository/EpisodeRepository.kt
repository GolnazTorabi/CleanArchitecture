package com.test.cleanArchRoomTest.episode.domain.repository

import com.test.cleanArchRoomTest.episode.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData
import io.reactivex.Maybe
import io.reactivex.Single

interface EpisodeRepository {
    fun getEpisode(id:String):Single<ResultsItemEpisode>
    fun getEpisodeFromDB(id:String):Maybe<EpisodeData>
    fun insertEpisode(data: EpisodeData):Maybe<Long>
    fun deleteAllEpisodes():Single<Int>

}