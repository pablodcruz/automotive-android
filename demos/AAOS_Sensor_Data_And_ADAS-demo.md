# Sensor Data & ADAS in Android Automotive - Demo

## Description

The purpose of this demo is to explore the collection of sensor data and how to listen to events triggered by sensors. This information will eventually trigger the stateflow of certain ADAS systems, notifying the user when these systems engage, disengage, or potentially disable if a necessary sensor is down.

## Prerequisites

- Basic knowledge of Android Automotive OS and its components.
- Experience with Android development, especially with handling sensor data.

## Objectives

- Understand the architecture and flow of sensor data management in Android Automotive.
- Implement basic sensor data collection and event listening.
- Explore and explain how to trigger ADAS notifications based on sensor events.
- Understand how to use ViewModel for managing UI-related data in a lifecycle-aware way.

## Setup and Testing

### Step 1: Set Up the Development Environment

- Ensure Android Studio is up-to-date and configured with the latest Android Automotive OS emulator.
- Familiarize yourself with the Android Automotive Emulator's extended controls, especially the sensor data simulation.

### Step 2: Create a Simple Sensor Data Collection App

1. **Initialize a New Project**: Start a new Android Automotive project in Android Studio.
2. **Develop the User Interface**: Design a simple interface using Jetpack Compose to display sensor data and status.

### Step 3: Test Sensor Data Collection

1. **Identifying Sensors and Sensor Capabilities**:
    ```kotlin
    private lateinit var sensorManager: SensorManager

    sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
    ```

2. **Test and check for Specific Sensor Availability**:
    ```kotlin
    if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
        // Success! There's a magnetometer.
    } else {
        // Failure! No magnetometer.
    }
    ```

## Steps to Complete

#### Step 1: Registering Sensor Event Listeners for light and accelerometer data

1. **Create a SensorViewModel**:
    ```kotlin
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
    }
    ```

2. **Create Composables to Display Sensor Data**:
    ```kotlin
    package com.example.adas_sensors_demo

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
    ```

3. **Set Up the Activity to Register Sensors and Use the ViewModel**:
    ```kotlin
    package com.example.adas_sensors_demo

    import android.hardware.Sensor
    import android.hardware.SensorEvent
    import android.hardware.SensorEventListener
    import android.hardware.SensorManager
    import android.os.Bundle
    import android.util.Log
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.viewModels
    import com.example.adas_sensors_demo.ui.theme.SensorDataADASDemoTheme
    import com.example.adas_sensors_demo.viewmodel.SensorViewModel

    class MainActivity : ComponentActivity(), SensorEventListener {

        private lateinit var sensorManager: SensorManager
        private var lightSensor: Sensor? = null
        private var accelerometer: Sensor? = null
        private val sensorViewModel: SensorViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                SensorDataADASDemoTheme {
                    SensorScreen(sensorViewModel)
                }
            }

            sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Do something here if sensor accuracy changes.
        }

        override fun onSensorChanged(event: SensorEvent) {
            when (event.sensor.type) {
                Sensor.TYPE_LIGHT -> {
                    val lux = event.values[0]
                    Log.d("SensorData", "Light sensor value: $lux")
                    sensorViewModel.updateLightSensorData(lux)
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    val acceleration = event.values[0]
                    Log.d("SensorData", "Accelerometer value: $acceleration")
                    if (acceleration > THRESHOLD) {
                        // Trigger ADAS event, e.g., collision warning
                    }
                    sensorViewModel.updateAccelerometerData(acceleration)
                }
            }
        }

        override fun onResume() {
            super.onResume()
            lightSensor?.also { sensor ->
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            }
            accelerometer?.also { sensor ->
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }

        override fun onPause() {
            super.onPause()
            sensorManager.unregisterListener(this)
        }

        companion object {
            private const val THRESHOLD = 15.0f
        }
    }
    ```

#### Step 2: Integrate with ADAS Systems

1. **Trigger ADAS Events**:
    ```kotlin
    override fun onSensorChanged(event: SensorEvent) {
        val acceleration = event.values[0]
        if (acceleration > THRESHOLD) {
            // Trigger ADAS event, e.g., collision warning
        }
    }
    ```

2. **Stateflow Management (Advanced)**: Implement stateflow to manage the engagement and disengagement of ADAS systems based on real-time sensor data.

#### Step 3: Test and Debug

1. **Run the Application**: Test the app on the Android Automotive emulator, focusing on how it interacts with simulated sensor data and ADAS notifications.
    - With the emulator running, open the Extended Controls panel by clicking on the "..." button on the emulator toolbar.
    - Navigate to `Virtual sensors > Light`.
    - Adjust the Light slider to simulate different light sensor values.
    - Navigate to `Device Pose` tab and test the accelerometer.

2. **Debugging**: Use Android Studio's logcat and debugging tools to troubleshoot and refine your application.

## Explanation of the ViewModel Usage

In this demo, the `SensorViewModel` is used to manage the state of sensor data in a lifecycle-aware manner. The ViewModel allows the app to:

- **Persist Sensor Data**: The ViewModel survives configuration changes such as screen rotations, ensuring that the sensor data is not lost.
- **Separate Concerns**: By moving the data handling and business logic to the ViewModel, the code in the activity becomes cleaner and focuses on the UI.
- **Reactive UI Updates**: The ViewModel exposes state flows for sensor data, which the composable functions observe and reactively update the UI whenever the data changes.

## Discussion Points

- Discuss/Document the challenges of managing and responding to real-time sensor data within a vehicle.
- Explore potential enhancements, such as predictive maintenance alerts based on sensor data trends.
- Explore and discuss how to implement the advanced steps from above.

## References

- [Android Automotive Docs: Sensors](https://source.android.com/docs/core/interaction/sensors)
- [Android Automotive Docs: Sensor Types](https://source.android.com/docs/core/interaction/sensors/sensor-types)
- [Android Studio Automotive Emulator - Extended Controls](https://developer.android.com/studio/run/emulator-extended-controls)
- [Video: Android Sensor Data Collection - Lights](https://youtu.be/_wa0Dar4O-k?si=1oGykgGQgJH31A1R)
- [Jetpack Compose Setup](https://developer.android.com/jetpack/compose/setup)
- [Android ViewModel Documentation](https://developer.android.com/topic/libraries/architecture/viewmodel)
