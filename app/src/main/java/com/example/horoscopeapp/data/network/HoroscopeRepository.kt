package com.example.horoscopeapp.data.network

import com.example.horoscopeapp.core.network.ResultType
import com.example.horoscopeapp.data.network.model.toDomain
import com.example.horoscopeapp.domain.dto.HoroscopeDTO
import com.example.horoscopeapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: HoroscopeApi) {
    fun getHoroscope(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> = flow {
        try {
            val response = api.getHoroscope(horoscopeDTO.sign, horoscopeDTO.date, horoscopeDTO.lang)
            if (response.isSuccessful) {
                response.body()?.let { horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }
            } else {
                val msg = when (response.code()) {
                    404 -> "Not found"
                    405 -> "Eres un subnormal"
                    else -> "Error generico"
                }
                emit(ResultType.Error(msg))
            }
        } catch (e: Exception) {
            val msg = when (e) {
                is IOException -> "Error IO"
                else -> "Crash final del mundo"
            }
            emit(ResultType.Error(msg))
        }
    }
}
