package com.example.app_retrofit.navigation

sealed class Screen(val route: String) {
    object AuthScreen : Screen("auth_screen")
    object ProductScreen : Screen("product_screen")
    object HomeScreen : Screen("home_screen")
    object ExploreScreen : Screen("explore_screen")
    object WishlistScreen : Screen("wishlist_screen")
    object OrdersScreen : Screen("orders_screen")
    object Cart : Screen("cart_screen")
}
