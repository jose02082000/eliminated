package com.example.horoscopeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.domain.GetHoroscopeUseCase
import com.example.horoscopeapp.presentation.detail.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState
    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase()
                .collect {
                    if (it != null) {
                        _uiState.value = DetailUiState.Success(it)
                    }
                }
        }
    }
}
