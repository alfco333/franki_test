package com.angelvargas.frankitest


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle

@Composable
fun WeatherScreen(weatherViewModel: WeatherViewModel) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<WeatherUiState>(
        initialValue = WeatherUiState.Loading,
        key1 = lifecycle,
        key2 = weatherViewModel
    ) {
        lifecycle.repeatOnLifecycle(
            state = Lifecycle.State.STARTED,
        ) {
            weatherViewModel.uiState.collect {
                value = it
            }
        }
    }

    when (uiState) {
        is WeatherUiState.Success -> {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Weather in ${(uiState as WeatherUiState.Success).weatherResponse.name} as is:",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Weather Description is: ${(uiState as WeatherUiState.Success).weatherResponse.weather[0].description}",
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Weather temp is: ${(uiState as WeatherUiState.Success).weatherResponse.main.temp}",
                    textAlign = TextAlign.Center
                )
            }
        }
        is WeatherUiState.Loading -> {
            CircularProgressIndicator()
        }

        is WeatherUiState.Error -> {
            (uiState as WeatherUiState.Error).throwable.printStackTrace()
        }
    }
}