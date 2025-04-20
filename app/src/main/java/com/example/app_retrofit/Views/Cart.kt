package com.example.app_retrofit.Views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app_retrofit.Model.Data.CartItem
import com.example.app_retrofit.ViewModels.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartVM: CartViewModel) {
    val cartItems by cartVM.cartItems.collectAsState()
    val subtotal = cartItems.sumOf { it.product.price * it.quantity }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Subtotal: $$subtotal",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { /* Handle checkout */ },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .height(48.dp)
                    ) {
                        Text("Proceed to Checkout")
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(cartItems) { cartItem ->
                    CartItemView(
                        cartItem = cartItem,
                        onRemove = { cartVM.removeFromCart(cartItem.product) },
                        onQuantityChange = { newQuantity ->
                            cartVM.updateQuantity(cartItem.product, newQuantity)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem, onRemove: () -> Unit, onQuantityChange: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {

            AsyncImage(
                model = cartItem.product.image,
                contentDescription = "Product Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {
                Text(cartItem.product.title, fontWeight = FontWeight.Bold)
                Text("Price: $${cartItem.product.price}", color = Color.Gray)

                Row {

                    IconButton(onClick = { if (cartItem.quantity > 1) onQuantityChange(cartItem.quantity - 1) }) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrement")
                    }
                    Text(cartItem.quantity.toString(), modifier = Modifier.align(Alignment.CenterVertically))
                    IconButton(onClick = { onQuantityChange(cartItem.quantity + 1) }) {
                        Icon(Icons.Default.Add, contentDescription = "Increment")
                    }
                }
            }


            IconButton(onClick = onRemove) {
                Icon(Icons.Default.Delete, contentDescription = "Remove Item")
            }
        }
    }
}
