package com.test.cleanArchRoomTest.domain.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class EpisodesWithCharacters(
    @Embedded val episode: EpisodeData,
    @Relation(
        parentColumn = "episodeId",
        entityColumn = "characterId",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val characters : List<CharactersData>

)