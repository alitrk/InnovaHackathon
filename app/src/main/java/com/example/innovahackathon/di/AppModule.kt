package com.example.innovahackathon.di

import com.example.innovahackathon.features.fetchCryptoFromApi.data.api.CryptoApi
import com.example.innovahackathon.features.fetchCryptoFromApi.data.datasource.CryptoDataSource
import com.example.innovahackathon.features.fetchCryptoFromApi.data.repo.CryptoRepository
import com.example.innovahackathon.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CryptoListModule {

    @Provides
    @Singleton
    fun provideCryptoApi(): CryptoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCryptoDataSource(api: CryptoApi): CryptoDataSource {
        return CryptoDataSource(api)
    }

    @Provides
    @Singleton
    fun provideCryptoRepository(cds: CryptoDataSource): CryptoRepository {
        return CryptoRepository(cds)
    }


}