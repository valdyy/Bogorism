package com.akhmad.bogorism2.data.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("placeResult")
	val placeResult: List<PlaceResultCategoryItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class PlaceResultCategoryItem(

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("image_a")
	val imageA: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("image_b")
	val imageB: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("place_id")
	val placeId: Int,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("long")
	val jsonMemberLong: Double
)
