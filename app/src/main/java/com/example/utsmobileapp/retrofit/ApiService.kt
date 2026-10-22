package com.example.utsmobileapp.retrofit

import com.example.utsmobileapp.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // GET https://dummyjson.com/products?limit=25&select=id,title,...
    @GET("products")
    fun getProducts(
        @Query("limit") limit: Int,
        @Query("select") select: String
    ): Call<ProductResponse>
}
