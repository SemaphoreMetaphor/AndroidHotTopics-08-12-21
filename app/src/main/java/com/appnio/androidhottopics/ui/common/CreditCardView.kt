package com.appnio.androidhottopics.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appnio.androidhottopics.data.CreditCard
import com.appnio.androidhottopics.data.creditCards
import com.appnio.androidhottopics.ui.theme.CreditCardDetailTextColor
import com.appnio.androidhottopics.ui.theme.PrimaryColor


@Composable
fun CreditCardView(card: CreditCard, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.58F)
            .background(PrimaryColor, RoundedCornerShape(20.dp))
    ) {
        AmountText(amount = card.amount)
        CardDetails(scope = this, card = card)
    }
}

@Composable
private fun AmountText(amount: Int) {
    Text(
        "$$amount",
        color = Color.White,
        fontSize = 22.sp,
        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
    )
}

@Composable
private fun CardDetails(scope: BoxScope, card: CreditCard) {
    with (scope) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 24.dp)
        ) {
            Text(
                card.name,
                color = CreditCardDetailTextColor,
                fontSize = 17.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                "${card.firstDigits} ●●●● ●●●● ${card.lastDigits}",
                color = CreditCardDetailTextColor,
                fontSize = 17.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CreditCard_Preview() {
    CreditCardView(creditCards[0])
}