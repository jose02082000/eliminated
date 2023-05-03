package com.example.horoscopeapp.data.network.model

import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    @SerializedName("date") var date: String? = null,
    @SerializedName("horoscope") var horoscope: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("sign") var sign: String? = null,
)

fun HoroscopeResponse.toDomain(): HoroscopeModel {
    return HoroscopeModel(
        horoscope = this.horoscope,
        icon = this.icon,
        sign = this.sign,
    )
}
