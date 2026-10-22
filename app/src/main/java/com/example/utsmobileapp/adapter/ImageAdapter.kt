package com.example.utsmobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.utsmobileapp.databinding.ImageItemBinding

class ImageAdapter(
    private val images: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = images[position]
        Glide.with(holder.itemView.context)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.imgProduct)
        holder.itemView.setOnClickListener { onClick(url) }
    }

    override fun getItemCount() = images.size
}
