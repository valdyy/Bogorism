package com.akhmad.bogorism2.ui.register

import androidx.lifecycle.ViewModel
import com.akhmad.bogorism2.data.reference.UserReference

class RegisterViewModel(private val userReference: UserReference): ViewModel() {

    fun register (name: String, email: String, password: String) = userReference.register(name, email, password)
}