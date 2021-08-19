package com.example.flobiz.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flobiz.databinding.ItemListCardBinding
import com.example.flobiz.models.Photo
import com.example.flobiz.viewholders.ListItemViewHolder

class ListRecyclerAdapter(
    private val data: List<Photo>,
) :
    RecyclerView.Adapter<ListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view =
            ItemListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}