package com.example.horoscopeapp.domain

import com.example.horoscopeapp.core.network.ResultType
import com.example.horoscopeapp.data.network.HoroscopeRepository
import com.example.horoscopeapp.domain.dto.HoroscopeDTO
import com.example.horoscopeapp.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {
    operator fun invoke(horoscopeDTO: HoroscopeDTO): Flow<ResultType<HoroscopeModel>> =
        horoscopeRepository.getHoroscope(horoscopeDTO)
}
