package com.akhmad.bogorism2.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akhmad.bogorism2.data.reference.UserReference
import com.akhmad.bogorism2.di.UserInjection
import com.akhmad.bogorism2.ui.category.NatureViewModel
import com.akhmad.bogorism2.ui.login.LoginViewModel
import com.akhmad.bogorism2.ui.main.MainViewModel
import com.akhmad.bogorism2.ui.map.MapViewModel
import com.akhmad.bogorism2.ui.register.RegisterViewModel
import com.akhmad.bogorism2.ui.search.SearchViewModel

class UserViewModelFactory(private val userRepository: UserReference):
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{

            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->{
                RegisterViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel() as T
            }
            modelClass.isAssignableFrom(MapViewModel::class.java) -> {
                MapViewModel() as T
            }
            modelClass.isAssignableFrom(NatureViewModel::class.java) -> {
                NatureViewModel() as T
            }





            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object{
        @Volatile
        private var instance : UserViewModelFactory? = null
        fun getInstance(context: Context): UserViewModelFactory =
            instance ?: synchronized(this){
                instance ?: UserViewModelFactory(UserInjection.provideReference(context))
            }.also { instance = it }
    }
}