package com.test.cleanArchRoomTest.domain.model

import androidx.room.Entity

@Entity(primaryKeys = ["episodeId","characterId"])
data class CharacterEpisodeCrossRef (
    val episodeId : Int,
    val characterId:Int
)