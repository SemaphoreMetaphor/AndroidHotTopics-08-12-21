package com.appnio.androidhottopics.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appnio.androidhottopics.ui.common.TransactionList
import com.appnio.androidhottopics.viewmodels.TransactionScreenActions.LoadData
import com.appnio.androidhottopics.viewmodels.TransactionScreenState
import com.appnio.androidhottopics.viewmodels.TransactionsViewModel


@Composable
fun TransactionsScreen(navigateUp: () -> Unit, viewModel: TransactionsViewModel = viewModel()) {
    val uiState: TransactionScreenState by viewModel.uiState.collectAsState()
    if (uiState.transactions.isEmpty()) {
        LaunchedEffect(viewModel) {
            viewModel.dispatch(LoadData)
        }
    }
    Scaffold(topBar = { TransactionTopAppBar(navigateUp) }) {
        Crossfade(targetState = uiState.loading, animationSpec = tween(500)) { loading ->
            if (loading) {
                LoadingProgressBar()
            } else {
                TransactionList(uiState.transactions)
            }
        }
    }
}

@Composable
private fun TransactionTopAppBar(navigateUp: () -> Unit) {
    TopAppBar(
        title = {
            Text("Transactions")
        },
        navigationIcon = {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        })
}

@Composable
private fun LoadingProgressBar() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
    }
}