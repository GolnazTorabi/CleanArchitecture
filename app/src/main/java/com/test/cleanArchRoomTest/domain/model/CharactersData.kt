package com.test.cleanArchRoomTest.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.cleanArchRoomTest.data.response.Location
import com.test.cleanArchRoomTest.data.response.Origin

@Entity(tableName = "Characters")
data class CharactersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val image: String? = null,
    val gender: String? = null,
    val url: String? = null,
    @Embedded(prefix = "org")
    val origin: Origin? = null,
    val name: String? = null,
    @Embedded(prefix = "loc")
    val location: Location? = null
)