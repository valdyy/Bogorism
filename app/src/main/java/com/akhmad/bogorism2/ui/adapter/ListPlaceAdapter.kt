package com.akhmad.bogorism2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhmad.bogorism2.data.entity.PlaceEntity
import com.akhmad.bogorism2.databinding.ItemPlaceBinding
import com.bumptech.glide.Glide

class ListPlaceAdapter (private val listPlace : ArrayList<PlaceEntity>): RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {

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
        val (placeName, rating, imageA) = listPlace[position]
        holder.listBinding.tvName.text = placeName
        holder.listBinding.tvRating.text = rating.toString()
        Glide.with(holder.listBinding.ivImagePlace.context)
            .load(imageA)
            .into(holder.listBinding.ivImagePlace)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPlace[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int =listPlace.size

    interface OnItemClickCallback {
        fun onItemClicked(data: PlaceEntity)
    }
}