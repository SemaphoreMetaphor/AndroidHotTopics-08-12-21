package com.appnio.androidhottopics.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min


@Composable
fun LetterCircleView(name: String, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .aspectRatio(1F)
            .background(stringToColor(name), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val size = min(maxWidth * 1.7f, maxHeight)
        val fontSize = size * 0.8f
        Text(
            name[0].toString(),
            color = Color.White,
            fontSize = LocalDensity.current.run { fontSize.toSp() },
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = -(size * 0.05f).value.dp)
        )
    }
}

private fun stringToColor(text: String): Color {
    val color = Color(0xFFFFFF and text.hashCode())
    return color.copy(alpha = 1F)
}

@Preview
@Composable
fun LetterCircleView_Preview() {
    LetterCircleView(name = "Brandon")
}