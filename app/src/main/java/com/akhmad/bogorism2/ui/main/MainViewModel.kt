package com.akhmad.bogorism2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.akhmad.bogorism2.data.reference.UserReference
import kotlinx.coroutines.launch

class MainViewModel(private val userReference: UserReference): ViewModel() {
    fun isLogin(): LiveData<Boolean> {
        return userReference.isLogin().asLiveData()
    }

    fun getToken() : LiveData<String> {
        return userReference.getToken().asLiveData()
    }

    fun logout(){
        viewModelScope.launch {
            userReference.logout()
        }
    }
}