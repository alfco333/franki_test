package com.angelvargas.frankitest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)
    var uiState: StateFlow<WeatherUiState> = _uiState.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        WeatherUiState.Loading
    )

    init {
        viewModelScope.launch {
            try {
                val response = weatherRepository.invoke()
                _uiState.emit(WeatherUiState.Success(response))
            } catch (e: Exception) {
                _uiState.emit(WeatherUiState.Error(e))
            }
        }
    }
}