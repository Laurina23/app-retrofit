package com.example.app_retrofit.ViewModels

import com.example.app_retrofit.Model.Data.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_retrofit.Model.API.RetrofitClient
import com.example.app_retrofit.Model.Repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val repository = UserRepository(RetrofitClient.apiService)

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.fetchUsers()
                _users.postValue(userList)
                println("Fetched Users: $userList")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}
