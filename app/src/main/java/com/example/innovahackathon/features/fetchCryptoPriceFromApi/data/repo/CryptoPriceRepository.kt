package com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.repo


import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.datasource.CryptoPriceDataSource
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.model.CryptoPrice
import com.example.innovahackathon.utils.Resource

class CryptoPriceRepository(private var cds: CryptoPriceDataSource) {

    suspend fun fetchCryptoPrice(symbol: String): Resource<CryptoPrice> = cds.fetchCryptoPrice(symbol)

}