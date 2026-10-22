package com.example.utsmobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.utsmobileapp.databinding.ProductItemBinding
import com.example.utsmobileapp.model.ProductsItem

class ProductAdapter :
    ListAdapter<ProductsItem, ProductAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ProductsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = getItem(position)

        holder.bind(product)

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(product)
        }
    }

    class MyViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductsItem) {
            binding.tvTitle.text = product.title.orEmpty()
            binding.tvCategory.text = "Kategori: ${product.category.orEmpty()}"
            binding.tvPrice.text = "Harga: $${product.price ?: "-"}"
            binding.tvRating.text = "Rating: ${product.rating ?: "-"}"

            Glide.with(itemView.context)
                .load(product.thumbnail)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(binding.imgThumbnail)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProductsItem>() {

            override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
