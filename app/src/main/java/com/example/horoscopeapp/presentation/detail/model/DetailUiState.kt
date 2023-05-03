package com.example.horoscopeapp.presentation.detail.model

import com.example.horoscopeapp.domain.model.HoroscopeModel

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val horoscopeModel: HoroscopeModel) : DetailUiState()
    data class Error(val msg: String) : DetailUiState()
}
