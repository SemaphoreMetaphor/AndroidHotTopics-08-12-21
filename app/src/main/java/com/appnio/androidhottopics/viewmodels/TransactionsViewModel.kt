package com.appnio.androidhottopics.viewmodels

import androidx.lifecycle.viewModelScope
import com.appnio.androidhottopics.data.Transaction
import com.appnio.androidhottopics.data.transactions
import com.appnio.androidhottopics.viewmodels.base.BaseViewModel
import com.appnio.androidhottopics.viewmodels.base.UiAction
import com.appnio.androidhottopics.viewmodels.base.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class TransactionsViewModel : BaseViewModel<TransactionScreenState, TransactionScreenActions>() {

    override val initialState: TransactionScreenState
        get() = TransactionScreenState()

    init {
        viewModelScope.launch {
            uiActions.collect {
                when (it) {
                    TransactionScreenActions.LoadData -> {
                        loadTransactions()
                    }
                }
            }
        }
    }

    private suspend fun loadTransactions() {
        setState(uiState.value.copy(loading = true))
        delay(1000)
        setState(uiState.value.copy(loading = false, transactions = transactions))
    }
}

data class TransactionScreenState(
    val loading: Boolean = true,
    val transactions: List<Transaction> = emptyList()
) : UiState

sealed class TransactionScreenActions : UiAction {
    object LoadData : TransactionScreenActions()
}