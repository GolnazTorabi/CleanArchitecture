package com.test.cleanArchRoomTest.character.data.response

import android.os.Parcelable
import androidx.room.TypeConverters
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.test.cleanArchRoomTest.utils.database.converter.StringConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseCharacter(

    @field:JsonProperty("results")
    val results: List<ResultsItem?>? = null,

    @field:JsonProperty("info")
    val info: Info? = null
) : Parcelable

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Origin(

    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
) : Parcelable

@Parcelize
data class Info(

    @field:JsonProperty("next")
    val next: String? = null,

    @field:JsonProperty("pages")
    val pages: Int? = null,

    @field:JsonProperty("prev")
    val prev: String? = null,

    @field:JsonProperty("count")
    val count: Int? = null
) : Parcelable

@Parcelize
@TypeConverters(StringConverter::class)
data class ResultsItem(

    @field:JsonProperty("image")
    val image: String? = null,

    @field:JsonProperty("gender")
    val gender: String? = null,

    @field:JsonProperty("species")
    val species: String? = null,

    @field:JsonProperty("created")
    val created: String? = null,

    @field:JsonProperty("origin")
    val origin: Origin? = null,

    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("location")
    val location: Location? = null,

    @field:JsonProperty("episode")
    val episode: List<String?>? = null,

    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("type")
    val type: String? = null,

    @field:JsonProperty("url")
    val url: String? = null,

    @field:JsonProperty("status")
    val status: String? = null
) : Parcelable

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class Location(

    @field:JsonProperty("name")
    var name: String? = null,

    @field:JsonProperty("url")
    val url: String? = null
) : Parcelable
