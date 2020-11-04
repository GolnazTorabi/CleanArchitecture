package com.test.cleanArchRoomTest.character.domain.model

import androidx.room.*
import com.test.cleanArchRoomTest.utils.database.converter.StringConverter
import com.test.cleanArchRoomTest.character.data.response.Location
import com.test.cleanArchRoomTest.character.data.response.Origin

@Entity(tableName = "Characters")
@TypeConverters(StringConverter::class)
data class CharactersData(
    @PrimaryKey(autoGenerate = true)
    val characterId: Int? = 0,
    var image: String? = null,
    val gender: String? = null,
    val url: String? = null,
    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "org")
    var origin: Origin? = null,
    var name: String? = null,
    @SuppressWarnings(RoomWarnings.DEFAULT_CONSTRUCTOR)
    @Embedded(prefix = "loc")
    var location: Location? = null,
    var status: String? = null,
    val episodes : List<String?>? = null
)