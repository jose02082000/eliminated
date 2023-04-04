package com.example.horoscopeapp.data.network.model

import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    @SerializedName("current_date") var currentDate: String? = null,
    @SerializedName("compatibility") var compatibility: String? = null,
    @SerializedName("lucky_time") var luckyTime: String? = null,
    @SerializedName("lucky_number") var luckyNumber: String? = null,
    @SerializedName("color") var color: String? = null,
    @SerializedName("date_range") var dateRange: String? = null,
    @SerializedName("mood") var mood: String? = null,
    @SerializedName("description") var description: String? = null,
)
