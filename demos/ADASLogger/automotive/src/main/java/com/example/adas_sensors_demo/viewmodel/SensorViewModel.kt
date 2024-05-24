package com.example.adas_sensors_demo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SensorViewModel : ViewModel() {
    private val _lightSensorData = MutableStateFlow(0f)
    val lightSensorData: StateFlow<Float> get() = _lightSensorData

    private val _accelerometerData = MutableStateFlow(0f)
    val accelerometerData: StateFlow<Float> get() = _accelerometerData

    // ADAS system state variables
    private val _adasEngaged = MutableStateFlow(false)
    val adasEngaged: StateFlow<Boolean> get() = _adasEngaged

    private val _adasDisengaged = MutableStateFlow(false)
    val adasDisengaged: StateFlow<Boolean> get() = _adasDisengaged

    private val _adasDisabled = MutableStateFlow(false)
    val adasDisabled: StateFlow<Boolean> get() = _adasDisabled

    fun updateLightSensorData(value: Float) {
        viewModelScope.launch {
            _lightSensorData.value = value
        }
    }

    fun updateAccelerometerData(value: Float) {
        viewModelScope.launch {
            _accelerometerData.value = value
        }
    }

    private fun manageAdasSystem(acceleration: Float) {
        viewModelScope.launch {
            when {
                acceleration > THRESHOLD -> {
                    _adasEngaged.value = true
                    _adasDisengaged.value = false
                    _adasDisabled.value = false
                }
                acceleration < -THRESHOLD -> {
                    _adasEngaged.value = false
                    _adasDisengaged.value = true
                    _adasDisabled.value = false
                }
                else -> {
                    _adasEngaged.value = false
                    _adasDisengaged.value = false
                    _adasDisabled.value = true
                }
            }
        }
    }
    companion object {
        private const val THRESHOLD = 15.0f
    }
}