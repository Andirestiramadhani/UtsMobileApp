package com.example.utsmobileapp.model

import com.example.utsmobileapp.response.Dimensions
import com.example.utsmobileapp.response.ReviewsItem
import com.google.gson.annotations.SerializedName

data class ProductsItem(

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("tags")
    val tags: List<String?>? = null,

    @field:SerializedName("reviews")
    val reviews: List<ReviewsItem?>? = null,

    @field:SerializedName("price")
    val price: Any? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("availabilityStatus")
    val availabilityStatus: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("stock")
    val stock: Int? = null,

    @field:SerializedName("dimensions")
    val dimensions: Dimensions? = null
)