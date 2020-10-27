package com.test.cleanArchRoomTest.data.mapper.episode

import com.test.cleanArchRoomTest.data.mapper.Mapper
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodeData

class EpisodeToDbMapper : Mapper<EpisodeData,ResultsItemEpisode>() {
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