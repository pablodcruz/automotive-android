package com.example.adas_sensors_demo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.adas_sensors_demo.viewmodel.SensorViewModel

@Composable
fun SensorScreen(viewModel: SensorViewModel) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SensorDataDisplay(viewModel)
            Divider(thickness = 1.dp)
            ADASDataDisplay(viewModel)
        }
    }
}

@Composable
fun SensorDataDisplay(viewModel: SensorViewModel) {
    val sensorValue = viewModel.lightSensorData.collectAsState().value
    Text(text = "Light sensor value: $sensorValue", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun ADASDataDisplay(viewModel: SensorViewModel) {
    val sensorValue = viewModel.accelerometerData.collectAsState().value
    Text(text = "Accelerometer value: $sensorValue", style = MaterialTheme.typography.bodyLarge)
}
