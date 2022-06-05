package com.akhmad.bogorism2.data.retrofit

import com.akhmad.bogorism2.data.response.LoginResponse
import com.akhmad.bogorism2.data.response.PlacesResponse
import com.akhmad.bogorism2.data.response.RegisterResponse
import com.akhmad.bogorism2.data.response.SearchResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse


    @GET("search")
    fun getPlaces(
        @Query("search") name: String
    ): Call<SearchResponse>


    @GET("places")
    fun getMarker() : Call<PlacesResponse>

    @GET("places/{category}")
    fun getNatural(
    )

}