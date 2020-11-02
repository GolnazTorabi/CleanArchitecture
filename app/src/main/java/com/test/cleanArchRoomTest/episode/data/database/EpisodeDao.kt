package com.test.cleanArchRoomTest.episode.data.database

import androidx.room.*
import com.test.cleanArchRoomTest.episode.domain.model.EpisodeData
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisode(episode: EpisodeData): Maybe<Long>

    @Query("Select * From Episode Where episodeId = :id")
    fun getSpecificEpisodes(id: String): Maybe<EpisodeData>

    @Query("DELETE FROM Episode")
    fun deleteAllEpisodes(): Single<Int>

   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEpisodes(episodes: List<EpisodeData>): Maybe<List<Long>>*/

/*    @Delete
    fun deleteAllEpisodes(episodes: List<EpisodeData>): Single<Int>

    @Query("DELETE FROM Episode")
    fun deleteAllEpisodes(): Single<Int>

    @Delete
    fun deleteSpecificEpisode(episodes: EpisodeData): Single<Int>

    @Update
    fun updateAllEpisodes(episodes: List<EpisodeData>): Single<Int>

    @Update
    fun updateSpecificEpisode(episodes: EpisodeData): Completable*/

    /*@Query("Select * From Episode")
    fun getAllEpisodes(): Maybe<List<EpisodeData>?>

    */
}