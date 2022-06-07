package com.akhmad.bogorism2.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.data.response.CategoryResponse
import com.akhmad.bogorism2.data.response.PlaceResultItem
import com.akhmad.bogorism2.data.response.PlacesResponse
import com.akhmad.bogorism2.data.response.SearchResponse
import com.akhmad.bogorism2.data.retrofit.ApiConfig
import com.akhmad.bogorism2.data.util.Load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NatureViewModel: ViewModel() {

    val _categoryList = MutableLiveData<List<PlaceEntity>>()
    val _isLoading = MutableLiveData<Boolean>()
    val _toastText = MutableLiveData<Load<String>>()

    val categoryModel: LiveData<List<PlaceEntity>> = _categoryList
    val isLoading: LiveData<Boolean> = _isLoading
    val toastText: LiveData<Load<String>> = _toastText

    fun getNature(category: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getNature(category)
        client.enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _categoryList.value = response.body() as List<PlaceEntity>
                } else {
                    _toastText.value = Load("Tidak ada data yang ditampilkan!")
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                _isLoading.value = false
                _toastText.value = Load("onFailure: ${t.message.toString()}")
            }
        })

    }
}