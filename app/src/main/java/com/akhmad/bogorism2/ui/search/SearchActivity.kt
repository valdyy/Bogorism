package com.akhmad.bogorism2.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.databinding.ActivitySearchBinding
import com.akhmad.bogorism2.ui.adapter.ListPlaceAdapter
import com.akhmad.bogorism2.ui.factory.UserViewModelFactory
import com.akhmad.bogorism2.ui.login.LoginActivity
import com.akhmad.bogorism2.ui.main.MainViewModel
import java.util.ArrayList


class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.search)

        val layoutManager = LinearLayoutManager(this)
        binding.mainSearch.listItem.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.mainSearch.listItem.addItemDecoration(itemDecoration)

        searchViewModel.searchModel.observe(this){
            setPlaceData(it)
        }

        searchViewModel.toastText.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            }
        }


        searchViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.place(query)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })



    }



    private fun setPlaceData(placeData: List<PlaceEntity>) {
        val  listUserAdapter = ListPlaceAdapter(placeData as ArrayList<PlaceEntity>)
        binding.mainSearch.listItem.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlaceEntity) {
                showSelectedUser(data)
            }
        })
    }
    private fun showSelectedUser(place: PlaceEntity) {
//        val detailUserIntent = Intent(this, DetailActivity::class.java)
//        detailUserIntent.putExtra(DetailActivity.EXTRA_PLACE, place.placeName)
//        startActivity(detailUserIntent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.mainSearch.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}