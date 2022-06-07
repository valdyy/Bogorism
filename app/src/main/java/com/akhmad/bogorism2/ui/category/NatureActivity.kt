package com.akhmad.bogorism2.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhmad.bogorism2.R
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.databinding.ActivityNatureBinding
import com.akhmad.bogorism2.ui.adapter.ListPlaceAdapter
import java.util.ArrayList

class NatureActivity : AppCompatActivity() {

    private val natureViewModel : NatureViewModel by viewModels()
    private lateinit var binding: ActivityNatureBinding
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.nature)

        natureViewModel.categoryModel.observe(this){
            setPlaceData(it)
        }

        natureViewModel.toastText.observe(this) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            }
        }


        natureViewModel.isLoading.observe(this) {
            showLoading(it)
        }

//        natureViewModel.categoryModel.observe(this){
//            natureViewModel.getNature(category)
//        }


        val layoutManager = LinearLayoutManager(this)
        binding.rvNature.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNature.addItemDecoration(itemDecoration)




    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setPlaceData(placeData: List<PlaceEntity>) {
        val  listPlaceAdapter = ListPlaceAdapter(placeData as ArrayList<PlaceEntity>)
        binding.rvNature.adapter = listPlaceAdapter
        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: PlaceEntity) {
                showSelectedPlace(data)
            }
        })
    }

    private fun showSelectedPlace(data: PlaceEntity) {
        TODO("Not yet implemented")
    }
}