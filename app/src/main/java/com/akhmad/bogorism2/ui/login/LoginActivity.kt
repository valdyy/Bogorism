package com.akhmad.bogorism2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.data.Event
import com.akhmad.bogorism2.databinding.ActivityLoginBinding
import com.akhmad.bogorism2.ui.factory.UserViewModelFactory
import com.akhmad.bogorism2.ui.main.MainActivity
import com.akhmad.bogorism2.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginModel: LoginViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        setupViewModel()
    }



    private fun setupAction() {
        binding.textButtonSignup.setOnClickListener {
            val  intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.emailEdit.text.toString().trim()
        val pass = binding.passwordEdit.text.toString().trim()
        when{
            email.isEmpty() ->{
                binding.emailEdit.error = resources.getString(R.string.message_validation, "email")

            }
            pass.isEmpty() -> {
                binding.passwordEdit.error = resources.getString(R.string.message_validation, "password")
            }


            else -> {
                loginModel.login(email, pass).observe(this){result ->
                    if (result != null){
                        when(result){
                            is Event.Loading -> {
                                showLoading(true)
                            }
                            is Event.Success -> {
                                showLoading(false)
                                val user = result.data
                                if (user.error){
                                    Toast.makeText(this@LoginActivity, user.message, Toast.LENGTH_SHORT).show()

                                } else {
                                    val token = user.loginResult.token
                                    loginModel.setToken(token, true)
                                }
                            }
                            is Event.Error -> {
                                showLoading(false)
                                Toast.makeText(this, resources.getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel(){
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        loginModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        loginModel.getToken().observe(this){ token ->
            if (token.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            emailEdit.isEnabled = !isLoading
            passwordEdit.isEnabled = !isLoading
            buttonLogin.isEnabled = !isLoading

            if (isLoading){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }

    }
}