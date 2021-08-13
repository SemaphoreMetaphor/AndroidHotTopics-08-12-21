package com.appnio.androidhottopics.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appnio.androidhottopics.data.Transaction


@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn(contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 25.dp)) {
        items(transactions.size, key = { it }) {
            TransactionItem(transaction = transactions[it])
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}