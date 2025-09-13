package com.krishisetu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

// Data classes defined at top-level for easier import
data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val date: String,
    val isCredit: Boolean
)

data class WalletData(
    val balance: Double,
    val lastUpdated: String,
    val transactions: List<Transaction>
)

sealed class WalletState {
    object Loading : WalletState()
    data class Success(val data: WalletData) : WalletState()
    data class Error(val message: String) : WalletState()
}

class WalletViewModel : ViewModel() {
    private val _state = MutableStateFlow<WalletState>(WalletState.Loading)
    val state: StateFlow<WalletState> = _state

    fun fetchWalletData() {
        viewModelScope.launch {
            _state.value = WalletState.Loading
            // Simulate network delay
            delay(1000) // kotlinx.coroutines.delay
            _state.value = WalletState.Success(
                WalletData(
                    balance = 1250.75,
                    lastUpdated = "2024-07-28 10:30 AM",
                    transactions = listOf(
                        Transaction("1", "Grocery Shopping", 50.25, "2024-07-28", false),
                        Transaction("2", "Salary Deposit", 1000.00, "2024-07-27", true),
                        Transaction("3", "Online Subscription", 15.50, "2024-07-26", false)
                    )
                )
            )
        }
    }
}
