package com.example.horoscopeapp.presentation.detail.model

import com.example.horoscopeapp.data.network.model.HoroscopeResponse

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val horoscopeResponse: HoroscopeResponse) : DetailUiState()
    data class Error(val msg: String) : DetailUiState()
}
