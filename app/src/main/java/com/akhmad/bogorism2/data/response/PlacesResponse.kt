package com.akhmad.bogorism2.data.response

import com.google.gson.annotations.SerializedName

data class PlacesResponse(

	@field:SerializedName("placeResult")
	val placeResult: List<PlaceResultItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class PlaceResultItem(

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("place_id")
	val placeId: Int,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("long")
	val jsonMemberLong: Double
)
