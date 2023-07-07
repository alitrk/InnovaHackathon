package com.example.innovahackathon.features.fetchCryptoPriceFromApi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.model.CryptoPrice
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.repo.CryptoPriceRepository
import com.example.innovahackathon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoPriceViewModel @Inject constructor(private var cRepo: CryptoPriceRepository) : ViewModel() {

    private val _cryptoPrice = MutableLiveData<Resource<CryptoPrice>>()
    val cryptoPrice: LiveData<Resource<CryptoPrice>>
        get() = _cryptoPrice

    fun fetchCryptoPrice(symbol: String) {
        viewModelScope.launch {
            _cryptoPrice.postValue(cRepo.fetchCryptoPrice(symbol))
        }
    }
}