package com.test.cleanArchRoomTest.domain.repository

import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import io.reactivex.Maybe
import io.reactivex.Single

interface EpisodeRepository {
    fun getEpisodes():Single<ResponseEpisodes>
    fun getEpisodesFromDB():Maybe<List<EpisodeData>?>

}