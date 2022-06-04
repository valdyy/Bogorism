package com.akhmad.bogorism2.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.data.Event
import com.akhmad.bogorism2.databinding.ActivityRegisterBinding
import com.akhmad.bogorism2.ui.factory.UserViewModelFactory
import com.akhmad.bogorism2.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        setupViewModel()

    }


    private fun setupViewModel(){
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        registerModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }


    private fun setupAction(){
        binding.buttonSignup.setOnClickListener {
            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val pass = binding.passwordEdit.text.toString().trim()

            when{
                name.isEmpty() -> {
                    binding.nameEdit.error = resources.getString(R.string.message_validation, "name")
                }

                email.isEmpty() -> {
                    binding.emailEdit.error = resources.getString(R.string.message_validation, "email")
                }

                pass.isEmpty() -> {
                    binding.passwordEdit.error = resources.getString(R.string.message_validation, "password")
                }

                else -> {
                    registerModel.register(name, email, pass).observe(this){result ->
                        if (result != null){
                            when(result){
                                is Event.Loading -> {
                                    showLoading(true)
                                }
                                is Event.Success -> {
                                    showLoading(false)
                                    val user = result.data
                                    if (user.error){
                                        Toast.makeText(this@RegisterActivity, user.message, Toast.LENGTH_SHORT).show()
                                    } else {
                                        AlertDialog.Builder(this@RegisterActivity).apply {
                                            setTitle(resources.getString(R.string.title_alert_dialog))
                                            setMessage(resources.getString(R.string.message_alert))
                                            setPositiveButton("Next") {_, _ ->
                                                finish()
                                            }
                                            create()
                                            show()
                                        }
                                    }
                                }
                                is Event.Error -> {
                                    showLoading(false)
                                    Toast.makeText(this, resources.getString(R.string.signup_error), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
        binding.textButtonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            nameEdit.isEnabled = !isLoading
            emailEdit.isEnabled = !isLoading
            passwordEdit.isEnabled = !isLoading
            buttonSignup.isEnabled = !isLoading

            if (isLoading){
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}