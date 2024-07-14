package com.example.app_retrofit.Model.Repository

import com.example.app_retrofit.Model.API.ApiService
import com.example.app_retrofit.Model.Data.User


class UserRepository(private val apiService: ApiService) {
    suspend fun fetchUsers(): List<User> {
        return apiService.fetchUsers()
    }
}