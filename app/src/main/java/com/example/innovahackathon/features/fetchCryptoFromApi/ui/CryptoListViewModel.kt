package com.example.innovahackathon.features.fetchCryptoFromApi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoItem
import com.example.innovahackathon.features.fetchCryptoFromApi.data.repo.CryptoRepository
import com.example.innovahackathon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private var cryptoRepository: CryptoRepository) : ViewModel() {

    private val _cryptoList = MutableStateFlow<Resource<List<CryptoItem>>>(Resource.Loading())
    val cryptoList: StateFlow<Resource<List<CryptoItem>>> = _cryptoList

    init {
        fetchCrypto()
    }

    private fun fetchCrypto() {
        viewModelScope.launch {
            cryptoRepository.fetchCrypto().stateIn(viewModelScope, SharingStarted.Eagerly, Resource.Loading())
                .collect { result ->
                    _cryptoList.value = result
                }
        }
    }
}
