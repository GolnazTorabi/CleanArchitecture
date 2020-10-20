package com.test.cleanArchRoomTest.data.response

import com.fasterxml.jackson.annotation.JsonProperty
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSpecificCharacter(

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
