package com.example.app_retrofit.Model.Data


    data class Product(
        val id: Int,
        val title: String,
        val price: Double,
        val description: String,
        val category: String,
        val image: String,
        val rating: Review
    )

    data class Review(
        val rate: Double,
        val count: Int
    )
