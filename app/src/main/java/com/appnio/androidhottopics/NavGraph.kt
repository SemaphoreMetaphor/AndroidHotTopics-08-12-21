package com.appnio.androidhottopics

import androidx.navigation.NavController
import com.appnio.androidhottopics.Destinations.Transactions


object Destinations {
    const val Home = "home"
    const val Transactions = "transactions"
}

class Actions(navController: NavController) {
    val navigateToAllTransactions = {
        navController.navigate(Transactions)
    }
    val navigateUp = {
        navController.popBackStack()
        Unit
    }
}