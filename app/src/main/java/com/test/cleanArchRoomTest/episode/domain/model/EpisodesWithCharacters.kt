package com.test.cleanArchRoomTest.episode.domain.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.test.cleanArchRoomTest.character.domain.model.CharactersData
import com.test.cleanArchRoomTest.episode.domain.model.CharacterEpisodeCrossRef
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData


data class EpisodesWithCharacters(
    @Embedded val episode: EpisodeData,
    @Relation(
        parentColumn = "episodeId",
        entityColumn = "characterId",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val characters : List<CharactersData>

)