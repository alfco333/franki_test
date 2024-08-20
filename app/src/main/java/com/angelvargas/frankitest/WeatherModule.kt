package com.angelvargas.frankitest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    fun provideWeatherService(retrofitClient: Retrofit): WeatherService =
        retrofitClient.create(WeatherService::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}