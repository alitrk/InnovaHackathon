package com.example.innovahackathon.di

import com.example.innovahackathon.features.fetchCryptoFromApi.data.api.CryptoApi
import com.example.innovahackathon.features.fetchCryptoFromApi.data.datasource.CryptoDataSource
import com.example.innovahackathon.features.fetchCryptoFromApi.data.repo.CryptoRepository
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.api.CryptoPriceApi
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.datasource.CryptoPriceDataSource
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.model.CryptoPrice
import com.example.innovahackathon.features.fetchCryptoPriceFromApi.data.repo.CryptoPriceRepository
import com.example.innovahackathon.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CryptoPriceModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoPriceApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoPriceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoDataSource(api: CryptoPriceApi): CryptoPriceDataSource {
        return CryptoPriceDataSource(api)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(cds: CryptoPriceDataSource): CryptoPriceRepository {
        return CryptoPriceRepository(cds)
    }


}