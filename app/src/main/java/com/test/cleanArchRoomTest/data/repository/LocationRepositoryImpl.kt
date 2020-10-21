package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.LocationApi
import com.test.cleanArchRoomTest.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationApi: LocationApi):LocationRepository {
}