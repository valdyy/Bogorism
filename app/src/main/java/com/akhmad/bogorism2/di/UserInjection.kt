package com.akhmad.bogorism2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.akhmad.bogorism2.data.reference.UserReference
import com.akhmad.bogorism2.data.retrofit.ApiConfig

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "settings")
object UserInjection {

    fun provideReference(context: Context):
            UserReference {
        val apiService = ApiConfig.getApiService()

        return UserReference.getInstance(context.dataStore, apiService)
    }
}