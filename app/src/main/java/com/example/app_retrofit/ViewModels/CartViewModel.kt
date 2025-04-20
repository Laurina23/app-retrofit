package com.example.app_retrofit.ViewModels

import androidx.lifecycle.ViewModel
import com.example.app_retrofit.Model.Data.CartItem
import com.example.app_retrofit.Model.Data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun addToCart(product: Product) {
        _cartItems.update { currentList ->
            val existingItem = currentList.find { it.product.id == product.id }
            if (existingItem != null) {
                currentList.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + 1)
                    else it
                }
            } else {
                currentList + CartItem(product, 1)
            }
        }
    }

    fun removeFromCart(product: Product) {
        _cartItems.update { it.filterNot { item -> item.product.id == product.id } }
    }

    fun updateQuantity(product: Product, quantity: Int) {
        _cartItems.update { list ->
            list.map {
                if (it.product.id == product.id) it.copy(quantity = quantity) else it
            }
        }
    }

    fun calculateSubtotal(): Double {
        return _cartItems.value.sumOf { it.product.price * it.quantity }
    }
}
