package com.example.app_retrofit.Model.Repository

import com.example.app_retrofit.Model.API.ApiService
import com.example.app_retrofit.Model.Data.Product


class ProductRepository(private val apiService: ApiService) {
    suspend fun fetchItems(): List<Product> {
        return apiService.fetchItems()
    }
}