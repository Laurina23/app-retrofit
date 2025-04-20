package com.example.app_retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_retrofit.ViewModels.CartViewModel
import com.example.app_retrofit.ViewModels.ProductViewModel
import com.example.app_retrofit.ViewModels.UserViewModel
import com.example.app_retrofit.Views.AuthScreen
import com.example.app_retrofit.Views.ProductScreen
import com.example.app_retrofit.Views.CartScreen
import com.example.app_retrofit.navigation.Screen
import com.example.app_retrofit.ui.theme.App_retrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val productVM: ProductViewModel by viewModels()
            val userVM: UserViewModel by viewModels()
            val cartVM: CartViewModel by viewModels()

            App_retrofitTheme {
                NavHost(navController = navController, startDestination = Screen.AuthScreen.route) {
                    composable(Screen.AuthScreen.route) {
                        AuthScreen(
                            onAuthSuccess = {
                                navController.navigate(Screen.ProductScreen.route)
                            }
                        )
                    }
                    composable(Screen.ProductScreen.route) {
                        ProductScreen(
                            productVM = productVM,
                            cartVM = cartVM,
                            navController = navController
                        )
                    }

                    composable(Screen.Cart.route) {
                        CartScreen(cartVM = cartVM)
                    }
                }
            }
        }
    }
}
