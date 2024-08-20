package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(val weatherDataSource: WeatherDataSource) {

    suspend fun invoke(): WeatherResponse = withContext(Dispatchers.IO) {
        weatherDataSource.getWeatherFromService()
    }
}