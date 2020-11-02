package com.test.cleanArchRoomTest.episode.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.test.cleanArchRoomTest.utils.database.converter.StringConverter

@Entity(tableName = "Episode")
@TypeConverters(StringConverter::class)
data class EpisodeData(
    @PrimaryKey(autoGenerate = true)
    var episodeId: Int? = null,
    var name: String? = null,
    var characters: List<String?>? = null,
    var episode: String? = null,
    var created: String? = null,
    var url: String? = null
)