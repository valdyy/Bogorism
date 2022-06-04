package com.akhmad.bogorism2.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.data.response.PlaceResultItem
import com.akhmad.bogorism2.data.response.PlacesResponse
import com.akhmad.bogorism2.data.response.SearchResponse
import com.akhmad.bogorism2.data.retrofit.ApiConfig
import com.akhmad.bogorism2.data.util.Load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel : ViewModel() {

    val _categoryList = MutableLiveData<PlacesResponse>()
    val _isLoading = MutableLiveData<Boolean>()
    val _toastText = MutableLiveData<Load<String>>()


    fun getMarker(){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getMarker()
        client.enqueue(object : Callback<PlacesResponse> {
            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _categoryList.value = response.body() as PlacesResponse
                } else {
                    _toastText.value = Load("Tidak ada data yang ditampilkan!")
                }
            }

            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Load("onFailure: ${t.message.toString()}")
            }
        })
    }
}