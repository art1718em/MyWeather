package com.example.network.dto

import com.google.gson.annotations.SerializedName

internal data class MainDto(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val temperatureFeelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
)