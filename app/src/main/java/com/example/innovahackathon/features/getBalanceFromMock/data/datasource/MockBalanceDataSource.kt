package com.example.innovahackathon.features.getBalanceFromMock.data.datasource

import com.example.innovahackathon.features.getBalanceFromMock.data.mock.MockBalanceApi
import com.example.innovahackathon.features.getBalanceFromMock.data.mock.MockBalanceData
import com.example.innovahackathon.features.getBalanceFromMock.data.mock.createMockApi
import com.example.innovahackathon.utils.MockNetworkInterceptor
import com.google.gson.Gson

class MockBalanceDataSource {
    fun mockBalanceApi(): MockBalanceApi {
        val mockData = MockBalanceData()
        val interceptor = MockNetworkInterceptor()
            .mock(
                "http://localhost/balance",
                { Gson().toJson(mockData.mockDataBalance1) },
                200,
                1000
            )
            .mock(
                "http://localhost/balance",
                { Gson().toJson(mockData.mockDataBalance2) },
                200,
                1000
            )
            .mock(
                "http://localhost/balance",
                { Gson().toJson(mockData.mockDataBalance3) },
                200,
                1000
            )
            .mock(
                "http://localhost/user-balance",
                { Gson().toJson(mockData.mockDataBalance1) },
                200,
                1000
            )

        return createMockApi(interceptor)
    }
}