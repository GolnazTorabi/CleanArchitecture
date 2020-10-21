package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.EpisodeApi
import com.test.cleanArchRoomTest.data.Api.LocationApi
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import com.test.cleanArchRoomTest.domain.repository.LocationRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(private val episodeApi: EpisodeApi):
    EpisodeRepository {
}