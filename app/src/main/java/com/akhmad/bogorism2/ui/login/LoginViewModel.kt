package com.akhmad.bogorism2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.akhmad.bogorism2.data.reference.UserReference
import kotlinx.coroutines.launch

class LoginViewModel(private val reference: UserReference): ViewModel() {

    fun login(email: String, password: String) = reference.login(email, password)

    fun setToken(token: String, isLogin: Boolean){
        viewModelScope.launch {
            reference.setToken(token, isLogin)
        }
    }

    fun getToken() : LiveData<String> {
        return reference.getToken().asLiveData()
    }

}