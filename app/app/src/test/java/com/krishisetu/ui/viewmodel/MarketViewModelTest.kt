package com.krishisetu.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class MarketViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testInitialState() = runTest {
        // TODO: Initialize MarketViewModel and assert initial state
        // val viewModel = MarketViewModel(...)
        // assertEquals(expected, viewModel.state.value)
    }
}
