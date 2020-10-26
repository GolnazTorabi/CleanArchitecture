package com.test.cleanArchRoomTest.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["episodeId","characterId"])
data class CharacterEpisodeCrossRef (
    val episodeId : Int,
    val characterId:Int
):Parcelable