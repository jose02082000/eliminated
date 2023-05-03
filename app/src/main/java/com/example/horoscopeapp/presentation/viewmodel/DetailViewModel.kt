package com.example.horoscopeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.core.network.ResultType
import com.example.horoscopeapp.domain.GetHoroscopeUseCase
import com.example.horoscopeapp.domain.dto.HoroscopeDTO
import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.example.horoscopeapp.presentation.detail.model.DetailUiState
import com.example.horoscopeapp.presentation.detail.model.DetailUiState.* // ktlint-disable no-wildcard-imports
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(Loading)
    val uiState: StateFlow<DetailUiState> = _uiState
    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(HoroscopeDTO(sign = "leo"))
                .collect { result: ResultType<HoroscopeModel> ->
                    when (result) {
                        is ResultType.Error -> {
                            _uiState.value = Error(result.msg ?: "error")
                        }
                        is ResultType.Success -> {
                            _uiState.value = Success(result.data!!)
                        }
                    }
                }
        }
    }
}
