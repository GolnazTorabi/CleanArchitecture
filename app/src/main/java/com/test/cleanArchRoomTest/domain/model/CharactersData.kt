package com.test.cleanArchRoomTest.domain.model

import androidx.room.*
import com.test.cleanArchRoomTest.data.database.converter.StringConverter
import com.test.cleanArchRoomTest.data.response.Location
import com.test.cleanArchRoomTest.data.response.Origin

@Entity(tableName = "Characters")
@TypeConverters(StringConverter::class)
data class CharactersData(
    @PrimaryKey(autoGenerate = true)
    val characterId: Int? = 0,
    val image: String? = null,
    val gender: String? = null,
    val url: String? = null,
    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "org")
    var origin: Origin? = null,
    val name: String? = null,
    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "loc")
    var location: Location? = null,
    val status: String? = null,
    val episodes : List<String?>? = null
)