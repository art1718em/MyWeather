package com.example.network.dto

import com.google.gson.annotations.SerializedName

internal data class WeatherDto(
    @SerializedName("description")
    val description: String,
)