package com.angelvargas.frankitest

import com.angelvargas.frankitest.data.response.WeatherResponse
import retrofit2.http.GET

interface WeatherService {

    @GET("/data/2.5/weather?q=Los%20Angeles&appid=5deca2f99f97d972562a33188d696b4a")
    suspend fun getWeather(): WeatherResponse
}