package com.appnio.androidhottopics.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appnio.androidhottopics.R
import com.appnio.androidhottopics.ui.common.CreditCardView
import com.appnio.androidhottopics.ui.common.TransactionItem
import com.appnio.androidhottopics.ui.theme.DividerColor
import com.appnio.androidhottopics.ui.theme.PrimaryColor
import com.appnio.androidhottopics.viewmodels.HomeScreenAction
import com.appnio.androidhottopics.viewmodels.HomeScreenState
import com.appnio.androidhottopics.viewmodels.HomeViewModel
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue


@Composable
fun HomeScreen(navigateToTransactions: () -> Unit, viewModel: HomeViewModel = viewModel()) {
    val uiState: HomeScreenState by viewModel.uiState.collectAsState()
    if (uiState.cards.isEmpty() && uiState.recentTransactions.isEmpty()) {
        LaunchedEffect(viewModel) {
            viewModel.dispatch(HomeScreenAction.LoadData)
        }
    }
    Column(Modifier.background(MaterialTheme.colors.primary)) {
        Header()
        Divider(color = DividerColor)
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                YourCards(uiState)
            }
            item {
                SeeAllSectionHeader("Recent Transactions", navigateToTransactions)
                Spacer(modifier = Modifier.height(16.dp))
            }
            recentTransactionItems(this, uiState)
        }
    }
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(94.dp)
            .padding(horizontal = 14.dp)
            .padding(bottom = 14.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Text(
            "Home",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 28.sp,
            modifier = Modifier.height(44.dp)
        )
        Row(modifier = Modifier.align(Alignment.BottomEnd)) {
            HeaderButton(R.drawable.ic_analytics)
            HeaderButton(R.drawable.ic_search)
            HeaderButton(R.drawable.ic_more)
        }
    }
}

@Composable
private fun HeaderButton(@DrawableRes resourceId: Int) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(id = resourceId), contentDescription = "", tint = MaterialTheme.colors.onPrimary)
    }
}

@Composable
private fun SeeAllSectionHeader(title: String, action: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            title,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Text(
            "See all",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 17.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { action() }
        )
    }
}

@Composable
private fun YourCards(uiState: HomeScreenState) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
    ) {
        SeeAllSectionHeader("Your Cards", {})
        Spacer(modifier = Modifier.height(16.dp))
        CardPager(uiState = uiState)
    }
}

@Composable
private fun CardProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.58F),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardPager(uiState: HomeScreenState) {
    val count = if (uiState.loadingCards) 1 else uiState.cards.size
    val pagerState = rememberPagerState(pageCount = count)
    Column {
        HorizontalPager(state = pagerState, itemSpacing = 16.dp) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        alphaScalePageTransformer(this@HorizontalPager, this, it)
                    }
            ) {
                Crossfade(
                    targetState = uiState.loadingCards,
                    animationSpec = tween(500)
                ) { loading ->
                    if (loading) {
                        CardProgressIndicator()
                    } else {
                        CreditCardView(uiState.cards[it])
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }
}

private fun recentTransactionItems(scope: LazyListScope, uiState: HomeScreenState) {
    with(scope) {
        if (uiState.loadingTransactions) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = PrimaryColor)
                }
            }
        } else {
            items(uiState.recentTransactions.size, key = { it }) { index ->
                TransactionItem(transaction = uiState.recentTransactions[index])
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun alphaScalePageTransformer(
    pagerScope: PagerScope,
    graphicScope: GraphicsLayerScope,
    page: Int
) {
    with(pagerScope) {
        with(graphicScope) {
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            ).also { scale ->
                scaleX = scale
                scaleY = scale
            }
            alpha = lerp(
                start = 0.5f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        }
    }
}

@Preview
@Composable
fun Header_Preview() {
    Header()
}