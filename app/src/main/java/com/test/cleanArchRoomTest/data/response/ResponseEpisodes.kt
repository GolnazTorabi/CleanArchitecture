package com.test.cleanArchRoomTest.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseEpisodes(
	val results: List<ResultsItemEpisode?>? = null,
	val info: Info? = null
) : Parcelable

@Parcelize
data class ResultsItemEpisode(
	val created: String? = null,
	val name: String? = null,
	val residents: List<String?>? = null,
	val id: Int? = null,
	val type: String? = null,
	val dimension: String? = null,
	val url: String? = null
) : Parcelable
