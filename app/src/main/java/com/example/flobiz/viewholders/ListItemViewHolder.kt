package com.example.flobiz.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.flobiz.databinding.ItemListCardBinding
import com.example.flobiz.models.Photo
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


class ListItemViewHolder(binding: ItemListCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val imageView: ShapeableImageView = binding.listItemImageView
    private val textView = binding.listItemTextView
    fun bindView(photo: Photo) {
        Picasso.get().load(photo.thumbnailUrl).into(imageView)
        textView.text = photo.title
    }
}