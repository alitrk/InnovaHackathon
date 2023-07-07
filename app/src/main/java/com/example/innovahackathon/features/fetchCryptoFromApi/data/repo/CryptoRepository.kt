package com.example.innovahackathon.features.fetchCryptoFromApi.data.repo

import com.example.innovahackathon.features.fetchCryptoFromApi.data.datasource.CryptoDataSource
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoItem
import com.example.innovahackathon.utils.Resource
import kotlinx.coroutines.flow.Flow

class CryptoRepository(private var cryptoDataSource: CryptoDataSource) {

    suspend fun fetchCrypto(): Flow<Resource<List<CryptoItem>>> = cryptoDataSource.fetchCrypto()
}