package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse
import javax.inject.Inject

class WeatherDataSource @Inject constructor(val weatherService: WeatherService) {

    suspend fun getWeatherFromService(): WeatherResponse =
        weatherService.getWeather()
}
