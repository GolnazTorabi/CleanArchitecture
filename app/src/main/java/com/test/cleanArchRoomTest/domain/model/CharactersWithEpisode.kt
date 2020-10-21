package com.test.cleanArchRoomTest.domain.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CharactersWithEpisode(
    @Embedded val character : CharactersData,
    @Relation(
        parentColumn = "characterId" ,
        entityColumn = "episodeId" ,
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val episodes : List<EpisodeData>
)