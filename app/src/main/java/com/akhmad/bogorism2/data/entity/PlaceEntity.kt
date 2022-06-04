package com.akhmad.bogorism2.data.entity

import com.google.gson.annotations.SerializedName

data class PlaceEntity(

    @field:SerializedName("place_name")
    val placeName: String,

    @field:SerializedName("rating")
    val rating: Double,

    )
