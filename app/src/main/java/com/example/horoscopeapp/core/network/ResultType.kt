package com.example.horoscopeapp.core.network

sealed class ResultType<T> {
    data class Success<T>(val data: T?) : ResultType<T>()
    data class Error<T>(val msg: String?) : ResultType<T>()
}
