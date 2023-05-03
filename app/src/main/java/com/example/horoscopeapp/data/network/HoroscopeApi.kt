package com.example.horoscopeapp.data.network

import com.example.horoscopeapp.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HoroscopeApi {
    @GET("/{sign}/")
    suspend fun getHoroscope(
        @Path("sign") sign: String,
        @Query("date") date: String,
        @Query("lang") lang: String,
    ): Response<HoroscopeResponse>
}
