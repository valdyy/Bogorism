package com.akhmad.bogorism2.data.response

import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("placeResult")
    val items: List<PlaceEntity?>? = null
)
