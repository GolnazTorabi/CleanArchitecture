package com.test.cleanArchRoomTest.data.database

import androidx.room.*
import com.test.cleanArchRoomTest.domain.model.CharactersData
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: CharactersData): Maybe<Long>

    @Delete
    fun deleteAllCharacters(characters: List<CharactersData>): Single<Int>

    @Delete
    fun deleteSpecificCharacter(characters: CharactersData): Single<Int>

    @Update
    fun updateAllCharacters(characters: List<CharactersData>): Single<Int>

    @Update
    fun updateSpecificCharacters(characters: CharactersData): Completable

    @Query("Select * From Characters")
    fun getAllCharacters(): Maybe<List<CharactersData>>

    @Query("Select * From Characters Where id = :id")
    fun getSpecificCharacters(id: Int): Maybe<CharactersData>
}