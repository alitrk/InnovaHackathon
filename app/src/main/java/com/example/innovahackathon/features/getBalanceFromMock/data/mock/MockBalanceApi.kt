package com.example.innovahackathon.features.getBalanceFromMock.data.mock

import com.example.innovahackathon.features.getBalanceFromMock.data.model.BalanceItem
import com.example.innovahackathon.utils.MockNetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MockBalanceApi {
    @GET("http://localhost/balance")
    suspend fun getBalance(): List<BalanceItem>
}

fun createMockApi(interceptor: MockNetworkInterceptor): MockBalanceApi {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MockBalanceApi::class.java)
}


