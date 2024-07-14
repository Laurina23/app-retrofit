package com.example.app_retrofit.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_retrofit.Model.Data.User
import com.example.app_retrofit.ViewModels.UserViewModel

@Composable
fun UserScreen(userVM: UserViewModel) {
    val users by userVM.users.observeAsState(emptyList())
    LazyColumn {
        items(users) {
            UserItem(user = it)
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "Name: ${user.name.firstname} ${user.name.lastname}")
            Text(text = "Email: ${user.email}")
            Text(text = "Username: ${user.userName}")
            Text(text = "Phone: ${user.phone}")
        }
    }
}
