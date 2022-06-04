package com.akhmad.bogorism2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.databinding.ItemPlaceBinding

class ListPlaceAdapter (private val listUser : ArrayList<PlaceEntity>): RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var listBinding : ItemPlaceBinding) : RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (placeName, rating) = listUser[position]
        holder.listBinding.tvName.text = placeName
        holder.listBinding.tvRating.text = rating.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int =listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: PlaceEntity)
    }
}