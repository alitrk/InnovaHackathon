package com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.datasource

import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.api.CryptoPriceApi
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.model.CryptoPrice
import com.example.innovahackathon.utils.Resource

class CryptoPriceDataSource (private var api: CryptoPriceApi){

    suspend fun fetchCryptoPrice(symbol: String): Resource<CryptoPrice> {
        return try {
            val response = api.fetchCryptoPrice(symbol)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Error")
            } else {
                Resource.Error("Error")
            }
        } catch (e: Exception) {
            Resource.Error("No data!")
        }
    }
}