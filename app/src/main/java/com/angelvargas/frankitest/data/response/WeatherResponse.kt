package com.angelvargas.frankitest.data.response

data class WeatherResponse(
    val weather: List<Weather>,
    val name: String,
    val main: Main
)
