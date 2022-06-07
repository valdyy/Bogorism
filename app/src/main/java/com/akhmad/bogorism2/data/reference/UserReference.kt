package com.akhmad.bogorism2.data.reference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.akhmad.bogorism2.data.Event
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.data.response.LoginResponse
import com.akhmad.bogorism2.data.response.RegisterResponse
import com.akhmad.bogorism2.data.retrofit.ApiService
import com.akhmad.bogorism2.data.util.Load
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserReference private constructor(
    private val dataStore: DataStore<Preferences>,
    private val apiService: ApiService
){



    fun register (name: String, email: String, password: String):
            LiveData<Event<RegisterResponse>> = liveData {
        emit(Event.Loading)
        try {
            val result = apiService.register(name, email, password)
            emit(Event.Success(result))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Event.Error(e.message.toString()))
        }
    }

    fun login (email: String, password: String):
            LiveData<Event<LoginResponse>> = liveData {
        emit(Event.Loading)
        try {
            val result = apiService.login(email, password)
            emit(Event.Success(result))
        }catch (e : Exception){
            e.printStackTrace()
            emit(Event.Error(e.message.toString()))
        }
    }



    fun getToken() : Flow<String> {
        return  dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    suspend fun setToken(token: String, isLogin: Boolean){
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
            preferences[STATE_KEY] = isLogin
        }
    }


    fun isLogin() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[STATE_KEY] ?: false
        }
    }

    suspend fun logout(){
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
            preferences[STATE_KEY] = false
        }
    }




    companion object{
        @Volatile
        private var INSTANCE: UserReference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private var TOKEN = stringPreferencesKey("token")
        private var STATE_KEY = booleanPreferencesKey("state")



        fun getInstance(
            dataStore: DataStore<Preferences>,
            apiService: ApiService
        ):UserReference{
            return INSTANCE ?: synchronized(this){
                val instance = UserReference(dataStore, apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}