package com.akhmad.bogorism2.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akhmad.bogorism2.databinding.ActivityNatureBinding

class NatureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNatureBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}