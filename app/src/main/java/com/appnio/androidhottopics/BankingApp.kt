package com.appnio.androidhottopics

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appnio.androidhottopics.Destinations.Home
import com.appnio.androidhottopics.Destinations.Transactions
import com.appnio.androidhottopics.ui.screens.HomeScreen
import com.appnio.androidhottopics.ui.screens.TransactionsScreen


@Composable
fun BankingApp() {
    val navController = rememberNavController()
    val actions = Actions(navController)
    NavHost(navController = navController, startDestination = Home) {
        composable(Home) {
            HomeScreen(actions.navigateToAllTransactions)
        }
        composable(Transactions) {
            TransactionsScreen(actions.navigateUp)
        }
    }
}