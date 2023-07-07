package com.example.innovahackathon.features.fetchCryptoFromApi.data.datasource
import com.example.innovahackathon.features.fetchCryptoFromApi.data.api.CryptoApi
import com.example.innovahackathon.features.fetchCryptoFromApi.data.model.CryptoItem
import com.example.innovahackathon.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CryptoDataSource(private var cryptoApi: CryptoApi) {
    suspend fun fetchCrypto(): Flow<Resource<List<CryptoItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val cryptoItems = cryptoApi.getCrypto().cryptoItems
                emit(Resource.Success(cryptoItems))
            } catch (e: IOException) {
                emit(Resource.Error("Network error: ${e.localizedMessage}"))
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    401 -> "Unauthorized"
                    404 -> "User not found"
                    else -> "Unknown error"
                }
                emit(Resource.Error("HTTP error (${e.code()}): $errorMessage"))
            } catch (e: Exception) {
                emit(Resource.Error("Unknown error: ${e.localizedMessage}"))
            }
        }
    }
}