package com.example.app_retrofit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_retrofit.Model.API.RetrofitClient
import com.example.app_retrofit.Model.Data.Product
import com.example.app_retrofit.Model.Repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val repository = ProductRepository(RetrofitClient.apiService)

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.fetchItems()
                _products.postValue(productList)
                println("Fetched Products: $productList")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}
