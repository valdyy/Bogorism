package com.akhmad.bogorism2.data

sealed class Event<out T> private constructor(){

    data class Success<out T>(val data: T): Event<T>()
    data class Error (val error: String): Event<Nothing>()
    object Loading : Event<Nothing>()
}