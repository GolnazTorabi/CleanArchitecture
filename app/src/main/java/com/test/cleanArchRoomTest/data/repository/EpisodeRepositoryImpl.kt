package com.test.cleanArchRoomTest.data.repository

import com.test.cleanArchRoomTest.data.Api.EpisodeApi
import com.test.cleanArchRoomTest.data.database.EpisodeDao
import com.test.cleanArchRoomTest.data.mapper.episode.EpisodeToDbMapper
import com.test.cleanArchRoomTest.data.response.ResponseEpisodes
import com.test.cleanArchRoomTest.data.response.ResultsItemEpisode
import com.test.cleanArchRoomTest.domain.model.EpisodeData
import com.test.cleanArchRoomTest.domain.repository.EpisodeRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val episodeDao: EpisodeDao
) : EpisodeRepository {

    override fun getEpisodes(): Single<ResponseEpisodes> {
        return episodeApi.getEpisodes()
    }

    override fun getEpisodesFromDB(): Maybe<List<EpisodeData>?> {
        return episodeDao.getAllEpisodes()
    }

   /* private fun insertEpisodes(data: List<ResultsItemEpisode?>): Maybe<List<Long>> {
        val list = EpisodeToDbMapper().reverseMap(data)
        return episodeDao.insertEpisodes(list)
    }*/

    /* private fun insertCharacterEpisode(data: List<ResultsItem?>): Maybe<List<Long>> {
         val list: MutableList<CharacterEpisodeCrossRef> = mutableListOf()
         data.forEach { character ->
             character?.episode?.forEach { episode ->
                 episode?.let {
                     list.add(CharacterEpisodeCrossRef(it.id!!, character.id!!))
                 }
             }
         }
         return characterEpisodeDao.insertCharacterEpisodes(list)

     }*/
}