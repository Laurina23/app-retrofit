package com.example.app_retrofit.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.app_retrofit.Model.Data.Product
import com.example.app_retrofit.ViewModels.ProductViewModel
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductScreen(productVM: ProductViewModel) {
    val products by productVM.products.observeAsState(emptyList())
    LazyColumn {
        items(products) {
            ProductItem(product = it)
        }
    }
}
@Composable
fun ProductItem(product: Product) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
    ){
        Column(modifier = Modifier
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .width(60.dp)

            )
            Text(text = product.title)
        }
    }
}