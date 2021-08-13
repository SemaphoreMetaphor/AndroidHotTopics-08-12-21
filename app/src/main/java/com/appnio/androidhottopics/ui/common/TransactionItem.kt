package com.appnio.androidhottopics.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appnio.androidhottopics.data.Transaction
import com.appnio.androidhottopics.data.transactions
import com.appnio.androidhottopics.ui.theme.Red
import com.appnio.androidhottopics.ui.theme.SubtitleColor


@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
    ) {
        LetterCircleView(name = transaction.name)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1F)
        ) {
            Text(transaction.name, color = MaterialTheme.colors.onPrimary, fontSize = 16.sp)
            Text(transaction.payee, color = SubtitleColor, fontSize = 12.sp)
        }
        Text(
            "- $${transaction.amount}",
            color = Red,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)
        )
    }
}

@Preview
@Composable
fun TransactionItem_Preview() {
    TransactionItem(transaction = transactions[0])
}