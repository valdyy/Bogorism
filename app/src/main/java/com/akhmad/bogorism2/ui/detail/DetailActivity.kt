package com.akhmad.bogorism2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akhmad.bogorism2.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        supportActionBar?.title = resources.getString(R.string.detail)

    }
}