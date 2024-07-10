package com.example.app_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val itemViewModel: ItemViewModel by viewModels()
            val products by itemViewModel.products.observeAsState(emptyList())
            App_retrofitTheme {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        itemViewModel.loadProducts()
                    }) {
                        Text(text = "Fetch Products")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    products.takeIf { it.isNotEmpty() }?.let {
                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "First Product Title: ${it[0].title}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "First Product Price: $${it[0].price}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
data class Item(
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
interface ApiService {
    @GET("products")
    suspend fun fetchItems(): List<Item>
}
object RetrofitClient {
    private const val BASE_URL = "https://fakestoreapi.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
class ItemRepository(private val apiService: ApiService) {
    suspend fun fetchItems(): List<Item> {
        return apiService.fetchItems()
    }
}
class ItemViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Item>>()
    val products: LiveData<List<Item>> get() = _products

    private val repository = ItemRepository(RetrofitClient.apiService)

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
