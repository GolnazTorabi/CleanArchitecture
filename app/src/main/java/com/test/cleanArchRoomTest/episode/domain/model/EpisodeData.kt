package com.test.cleanArchRoomTest.episode.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.test.cleanArchRoomTest.utils.database.converter.StringConverter

@Entity(tableName = "Episode")
@TypeConverters(StringConverter::class)
data class EpisodeData(
    @PrimaryKey(autoGenerate = true)
    val episodeId: Int? = null,
    val name: String? = null,
    val characters: List<String?>? = null,
    val episode: String? = null,
    val created: String? = null,
    val url: String? = null
)