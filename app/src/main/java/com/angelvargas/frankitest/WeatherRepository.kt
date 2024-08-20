package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(val weatherDataSource: WeatherDataSource) {

    fun invoke(): Flow<WeatherResponse> = weatherDataSource.getWeatherFromService()
}