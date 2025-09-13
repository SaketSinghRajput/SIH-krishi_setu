package com.krishisetu.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krishisetu.ui.viewmodel.WalletViewModel
import com.krishisetu.ui.viewmodel.WalletState
import com.krishisetu.ui.viewmodel.Transaction // Added import

@Composable
fun WalletScreen(viewModel: WalletViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWalletData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Wallet",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(Modifier.height(24.dp))

        when (val currentState = state) {
            is WalletState.Loading -> CircularProgressIndicator()
            is WalletState.Success -> {
                val walletData = currentState.data
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.AccountBalanceWallet, contentDescription = "Wallet Balance", tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(40.dp))
                            Spacer(Modifier.width(12.dp))
                            Text("Balance: ₹${walletData.balance}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("Last Updated: ${walletData.lastUpdated}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    }
                }
                Spacer(Modifier.height(24.dp))
                Text("Recent Transactions", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
                Spacer(Modifier.height(10.dp))
                if (walletData.transactions.isEmpty()) {
                    Text("No recent transactions.", style = MaterialTheme.typography.bodyMedium)
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
                        items(walletData.transactions) { transaction ->
                            TransactionItem(transaction)
                            Divider()
                        }
                    }
                }
            }
            is WalletState.Error -> {
                Text("Error: ${currentState.message}", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) { // Changed parameter type
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Receipt, contentDescription = "Transaction", tint = MaterialTheme.colorScheme.secondary)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(transaction.description, style = MaterialTheme.typography.bodyLarge)
                    Text(transaction.date, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Text(
                text = "${if (transaction.isCredit) "+" else "-"}₹${transaction.amount}",
                style = MaterialTheme.typography.bodyLarge,
                color = if (transaction.isCredit) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
            )
        }
    }
}

// Dummy data for WalletViewModel and WalletState if not defined elsewhere for preview
// This section has been removed.
