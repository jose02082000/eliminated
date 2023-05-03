package com.example.horoscopeapp.domain

import com.example.horoscopeapp.data.network.HoroscopeApi
import com.example.horoscopeapp.data.network.model.HoroscopeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val api: HoroscopeApi) {

    suspend operator fun invoke(): Flow<HoroscopeResponse?> {
        val response = api.getHoroscope("leo", "today")
        if (response.isSuccessful) {
            return flowOf(response.body())
        }
        return flowOf(null)
    }
}
