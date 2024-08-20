package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(
        val weatherResponse: WeatherResponse
    ) : WeatherUiState()

    data class Error(val throwable: Throwable) : WeatherUiState()
}
