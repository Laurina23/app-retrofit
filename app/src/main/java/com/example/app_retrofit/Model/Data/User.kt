package com.example.app_retrofit.Model.Data

import com.google.gson.annotations.SerializedName


data class User(
    val id: Int,
    val email: String,
    @SerializedName("username")
    val userName: String,
    val password: String,
    val name: Name,
    val phone: String,
)
data class Name(
    val firstname: String,
    val lastname: String
)
