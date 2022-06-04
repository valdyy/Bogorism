package com.akhmad.bogorism2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.databinding.ActivityMainBinding
import com.akhmad.bogorism2.ui.factory.UserViewModelFactory
import com.akhmad.bogorism2.ui.login.LoginActivity
import com.akhmad.bogorism2.ui.map.MapsActivity
import com.akhmad.bogorism2.ui.category.NatureActivity
import com.akhmad.bogorism2.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.catNature.setOnClickListener{
            val intent = Intent(this, NatureActivity::class.java)
            startActivity(intent)
        }



        setupViewModel()
    }

    private fun setupViewModel() {
        val factory: UserViewModelFactory = UserViewModelFactory.getInstance(this)
        mainViewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]
        mainViewModel.isLogin().observe(this){
            if (!it){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                val intent = Intent(this, SearchActivity::class.java)
//                intent.putExtra(MapsActivity.EXTRA_TOKEN, token)
                startActivity(intent)
                true
            }
            R.id.menu_map -> {
                val intent = Intent(this, MapsActivity::class.java)
//                intent.putExtra(MapsActivity.EXTRA_TOKEN, token)
                startActivity(intent)
                true
            }
            R.id.menu_logout -> {
                mainViewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            else -> false
        }
    }
}