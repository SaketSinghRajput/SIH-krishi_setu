package com.krishisetu.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AdvisorViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testInitialStateIsLoading() = runTest {
        val vm = AdvisorViewModel()
        assertTrue(vm.state.value is AdvisorState.Loading)
    }
    // Add more tests for API success/error as needed
}
