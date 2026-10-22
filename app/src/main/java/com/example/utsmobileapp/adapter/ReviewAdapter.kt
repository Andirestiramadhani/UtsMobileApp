package com.example.utsmobileapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsmobileapp.databinding.ItemReviewBinding
import com.example.utsmobileapp.response.ReviewsItem

class ReviewAdapter(
    private val reviews: List<ReviewsItem>
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.binding.tvReviewerName.text = review.reviewerName.orEmpty()
        holder.binding.tvReviewerEmail.text = review.reviewerEmail.orEmpty()
        holder.binding.tvRating.text = "Rating: ${review.rating ?: "-"}/5"
        holder.binding.tvComment.text = "\"${review.comment.orEmpty()}\""
        holder.binding.tvDate.text = review.date.orEmpty()
    }

    override fun getItemCount(): Int = reviews.size
}
