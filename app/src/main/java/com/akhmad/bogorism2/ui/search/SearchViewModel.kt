package com.akhmad.bogorism2.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.data.response.SearchResponse
import com.akhmad.bogorism2.data.retrofit.ApiConfig
import com.akhmad.bogorism2.data.util.Load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {

    private val _categoryList = MutableLiveData<List<PlaceEntity>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _toastText = MutableLiveData<Load<String>>()

    val searchModel: LiveData<List<PlaceEntity>> = _categoryList
    val isLoading: LiveData<Boolean> = _isLoading
    val toastText: LiveData<Load<String>> = _toastText

    fun place (name: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPlaces(name)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _categoryList.value = response.body()?.items as List<PlaceEntity>
                } else {
                    _toastText.value = Load("Tidak ada data yang ditampilkan!")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Load("onFailure: ${t.message.toString()}")
            }
        })
    }
}