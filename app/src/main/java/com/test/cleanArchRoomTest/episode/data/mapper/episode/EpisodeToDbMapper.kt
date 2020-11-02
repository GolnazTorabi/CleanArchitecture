package com.test.cleanArchRoomTest.episode.data.mapper.episode

import com.test.cleanArchRoomTest.utils.database.Mapper
import com.test.cleanArchRoomTest.episode.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData

class EpisodeToDbMapper : Mapper<EpisodeData, ResultsItemEpisode>() {
    override fun map(value: EpisodeData?): ResultsItemEpisode {
        throw UnsupportedOperationException()
    }

    override fun reverseMap(value: ResultsItemEpisode?): EpisodeData? {
         return if(value == null){
            null
        } else {
             EpisodeData(
                 value.id,
                 value.name,
                 value.characters,
                 value.episode,
                 value.created,
                 value.url
             )
         }
    }
}