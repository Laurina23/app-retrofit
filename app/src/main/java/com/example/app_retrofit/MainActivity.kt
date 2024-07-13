package com.example.app_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.app_retrofit.ui.theme.App_retrofitTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app_retrofit.Model.API.RetrofitClient
import com.example.app_retrofit.Model.Data.Product
import com.example.app_retrofit.Model.Repository.ProductRepository
import com.example.app_retrofit.ViewModels.ProductViewModel
import com.example.app_retrofit.Views.ProductScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productVM: ProductViewModel by viewModels()
            App_retrofitTheme {
                ProductScreen(productVM)
//                Column(
//                    modifier = Modifier
//                        .padding(20.dp)
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Button(onClick = {
//                        productViewModel.loadProducts()
//                    }) {
//                        Text(text = "Fetch Products")
//                    }
//                    Spacer(modifier = Modifier.height(20.dp))
//                    products.takeIf { it.isNotEmpty() }?.let {
//                        Card(
//                            modifier = Modifier
//                                .padding(16.dp)
//                                .fillMaxWidth(),
//                            elevation = CardDefaults.cardElevation(8.dp)
//                        ) {
//                            Column(
//                                modifier = Modifier.padding(16.dp)
//                            ) {
//                                Text(
//                                    text = "First Product Title: ${it[0].title}",
//                                    style = MaterialTheme.typography.titleMedium
//                                )
//                                Spacer(modifier = Modifier.height(8.dp))
//                                Text(
//                                    text = "First Product Price: $${it[0].price}",
//                                    style = MaterialTheme.typography.titleMedium
//                                )
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}
