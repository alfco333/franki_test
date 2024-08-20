package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherDataSource @Inject constructor(val weatherService: WeatherService) {

    fun getWeatherFromService(): Flow<WeatherResponse> =
        flow<WeatherResponse> {
            weatherService.getWeather()
        }.flowOn(Dispatchers.IO)
}
