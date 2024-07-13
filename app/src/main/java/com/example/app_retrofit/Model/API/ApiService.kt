package com.example.app_retrofit.Model.API
import com.example.app_retrofit.Model.Data.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun fetchItems(): List<Product>
}
