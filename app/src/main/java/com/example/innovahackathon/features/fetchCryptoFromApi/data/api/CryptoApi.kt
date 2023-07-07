package com.example.innovahackathon.features.fetchCryptoFromApi.data.api

import com.example.innovahackathon.BuildConfig.API_KEY
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoList
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {


    @GET("crypto/symbol")
    suspend fun getCrypto(
        @Query("exchange") exchange: String = "binance",
        @Query("token") token: String = API_KEY
    ): CryptoList

    /*@GET("quote")
    suspend fun getCryptoPrice(
        @Query("symbol") symbol: String,
        @Query("token") token: String = API_KEY
    ): CryptoPrice*/

}