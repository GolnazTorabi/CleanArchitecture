package com.test.cleanArchRoomTest.episode.data.response

import com.fasterxml.jackson.annotation.JsonProperty
import android.os.Parcelable
import com.test.cleanArchRoomTest.character.data.response.Info
import com.test.cleanArchRoomTest.character.data.response.ResultsItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseEpisodes(

    @field:JsonProperty("results")
	val results: List<ResultsItem?>? = null,

    @field:JsonProperty("info")
	val info: Info? = null
) : Parcelable



@Parcelize
data class ResultsItemEpisode(

	@field:JsonProperty("air_date")
	val airDate: String? = null,

	@field:JsonProperty("characters")
	val characters: List<String?>? = null,

	@field:JsonProperty("created")
	val created: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("episode")
	val episode: String? = null,

	@field:JsonProperty("id")
	val id: Int? = null,

	@field:JsonProperty("url")
	val url: String? = null
) : Parcelable
