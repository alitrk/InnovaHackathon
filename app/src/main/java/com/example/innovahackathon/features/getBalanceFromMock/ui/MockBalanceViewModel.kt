package com.example.innovahackathon.features.getBalanceFromMock.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovahackathon.features.getBalanceFromMock.data.datasource.MockBalanceDataSource
import com.example.innovahackathon.features.getBalanceFromMock.data.model.BalanceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MockBalanceViewModel @Inject constructor(private var mds: MockBalanceDataSource) : ViewModel() {

    private val _balanceItems = MutableLiveData<List<BalanceItem>>()
    val balanceItems: LiveData<List<BalanceItem>> = _balanceItems

    init {
        fetchBalanceItems()
    }
    private fun fetchBalanceItems() {
        viewModelScope.launch {
            try {
                val balanceItems = mds.mockBalanceApi().getBalance()
                _balanceItems.value = balanceItems
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}