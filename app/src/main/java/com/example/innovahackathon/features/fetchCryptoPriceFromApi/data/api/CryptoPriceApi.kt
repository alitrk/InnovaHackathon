package com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.api

import com.example.innovahackathon.BuildConfig.API_KEY
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.model.CryptoPrice
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoPriceApi {

    @GET("quote")
    suspend fun fetchCryptoPrice(
        @Query("symbol") symbol: String,
        @Query("token") token: String = API_KEY
    ): Response<CryptoPrice>

}