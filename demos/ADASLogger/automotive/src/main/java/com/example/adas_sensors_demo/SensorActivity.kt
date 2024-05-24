//package com.example.adas_sensors_demo
//
//import android.hardware.Sensor
//import android.hardware.SensorEvent
//import android.hardware.SensorEventListener
//import android.hardware.SensorManager
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import com.example.adas_sensors_demo.ui.SensorScreen
//import com.example.adas_sensors_demo.ui.theme.SensorDataADASDemoTheme
//import com.example.adas_sensors_demo.viewmodel.SensorViewModel
//
//class SensorActivity : ComponentActivity(), SensorEventListener {
//
//    private lateinit var sensorManager: SensorManager
//    private var lightSensor: Sensor? = null
//    private var accelerometer: Sensor? = null
//    private val sensorViewModel: SensorViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SensorDataADASDemoTheme {
//                SensorScreen(sensorViewModel)
//            }
//        }
//
//        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
//        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
//        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
//        // Do something here if sensor accuracy changes.
//    }
//
//    override fun onSensorChanged(event: SensorEvent) {
//        when (event.sensor.type) {
//            Sensor.TYPE_LIGHT -> {
//                val lux = event.values[0]
//                sensorViewModel.updateLightSensorData(lux)
//            }
//            Sensor.TYPE_ACCELEROMETER -> {
//                val acceleration = event.values[0]
//                if (acceleration > THRESHOLD) {
//                    // Trigger ADAS event, e.g., collision warning
//                }
//                sensorViewModel.updateAccelerometerData(acceleration)
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        lightSensor?.also { sensor ->
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//        accelerometer?.also { sensor ->
//            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        sensorManager.unregisterListener(this)
//    }
//
//    companion object {
//        private const val THRESHOLD = 15.0f
//    }
//}
