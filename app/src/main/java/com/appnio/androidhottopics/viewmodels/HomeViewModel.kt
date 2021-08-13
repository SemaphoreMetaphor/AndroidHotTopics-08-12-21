package com.appnio.androidhottopics.viewmodels

import androidx.lifecycle.viewModelScope
import com.appnio.androidhottopics.data.CreditCard
import com.appnio.androidhottopics.data.Transaction
import com.appnio.androidhottopics.data.creditCards
import com.appnio.androidhottopics.data.transactions
import com.appnio.androidhottopics.viewmodels.base.BaseViewModel
import com.appnio.androidhottopics.viewmodels.base.UiAction
import com.appnio.androidhottopics.viewmodels.base.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeViewModel :
    BaseViewModel<HomeScreenState, HomeScreenAction>() {

    override val initialState: HomeScreenState
        get() = HomeScreenState()

    init {
        viewModelScope.launch {
            uiActions.collect { action ->
                when (action) {
                    HomeScreenAction.LoadData -> {
                        setState(uiState.value.copy(loadingCards = true, loadingTransactions = true))
                        loadCreditCards()
                        loadRecentTransactions()
                    }
                }
            }
        }
    }

    private fun loadCreditCards() {
        viewModelScope.launch {
            delay(1000)
            setState(uiState.value.copy(loadingCards = false, cards = creditCards))
            setState(uiState.value.copy(loadingCards = false))
        }
    }

    private fun loadRecentTransactions() {
        viewModelScope.launch {
            delay(1500)
            setState(uiState.value.copy(loadingTransactions = false, recentTransactions = transactions.subList(0, 5)))
        }
    }
}


data class HomeScreenState(
    val loadingCards: Boolean = true,
    val loadingTransactions: Boolean = true,
    val cards: List<CreditCard> = emptyList(),
    val recentTransactions: List<Transaction> = emptyList()
) : UiState

sealed class HomeScreenAction : UiAction {
    object LoadData : HomeScreenAction()
}