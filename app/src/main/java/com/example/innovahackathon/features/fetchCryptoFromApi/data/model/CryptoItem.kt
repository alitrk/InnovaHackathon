package com.example.innovahackathon.features.fetchCryptoFromApi.data.model

import java.io.Serializable

data class CryptoItem(
    val description: String,
    val displaySymbol: String,
    val symbol: String
) : Serializable