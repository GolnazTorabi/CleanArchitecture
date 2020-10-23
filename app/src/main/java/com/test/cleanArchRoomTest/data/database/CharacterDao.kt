package com.test.cleanArchRoomTest.data.database

import androidx.room.*
import com.test.cleanArchRoomTest.domain.model.CharactersData
import com.test.cleanArchRoomTest.domain.model.CharactersWithEpisode
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characters: CharactersData): Maybe<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharactersData>): Maybe<List<Long>>

    @Delete
    fun deleteAllCharacters(characters: List<CharactersData>): Single<Int>

    @Query("DELETE FROM Characters")
    fun deleteAllCharacters(): Single<Int>

    @Delete
    fun deleteSpecificCharacter(characters: CharactersData): Single<Int>

    @Update
    fun updateAllCharacters(characters: List<CharactersData>): Single<Int>

    @Update
    fun updateSpecificCharacters(characters: CharactersData): Completable

    @Query("Select * From Characters")
    fun getAllCharacters(): Maybe<List<CharactersData>?>

    @Query("Select * From Characters Where characterId = :id")
    fun getSpecificCharacters(id: String): Maybe<CharactersData>

    @Transaction
    @Query("SELECT * FROM Characters")
    fun getCharactersWithEpisodes(): List<CharactersWithEpisode>
}