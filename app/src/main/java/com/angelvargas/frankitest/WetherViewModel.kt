package com.angelvargas.frankitest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(weatherRepository: WeatherRepository) : ViewModel() {

    var uiState: StateFlow<WeatherUiState> = weatherRepository.invoke().map {
        WeatherUiState.Success(it)
    }.catch {
        WeatherUiState.Error(it)
    }.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        WeatherUiState.Loading
    )
}