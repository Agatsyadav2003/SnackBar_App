package com.ex.deliveryfood

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ex.deliveryfood.ui.theme.Screens.AuthScreen
import com.ex.deliveryfood.ui.theme.Screens.FoodScreen
import com.ex.deliveryfood.ui.theme.Screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("main", Context.MODE_PRIVATE)
    val userLoggedIn = sharedPreferences.getBoolean("loggedIn",false)

    NavHost(navController = navController, startDestination = if (userLoggedIn) "home" else "auth"){
        composable("auth"){
            AuthScreen(navController = navController)
        }
        composable("home"){
            HomeScreen(navController = navController)
        }
        composable("food"){
            FoodScreen(navController = navController)
        }
    }
}

