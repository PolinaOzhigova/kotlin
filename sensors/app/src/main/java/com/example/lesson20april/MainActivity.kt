package com.example.lesson20april

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson20april.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var sensorAdapter: SensorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        setupSpinner()
        setupRecyclerView()
    }

    private fun setupSpinner() {
        val categories = listOf("Датчики окружающей среды", "Датчики положения устройства", "Датчики состояния человека")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = adapter

        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                displaySensors()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupRecyclerView() {
        sensorAdapter = SensorAdapter()
        binding.sensorRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.sensorRecyclerView.adapter = sensorAdapter
    }

    private fun displaySensors() {
        val selectedCategory = binding.categorySpinner.selectedItem.toString()
        val sensors = when (selectedCategory) {
            "Датчики окружающей среды" -> getEnvironmentSensors()
            "Датчики положения устройства" -> getPositionSensors()
            "Датчики состояния человека" -> getHumanSensors()
            else -> emptyList()
        }
        sensorAdapter.submitList(sensors)
    }

    private fun getEnvironmentSensors(): List<Sensor> {
        return sensorManager.getSensorList(Sensor.TYPE_ALL).filter { sensor ->
            when (sensor.type) {
                Sensor.TYPE_AMBIENT_TEMPERATURE,
                Sensor.TYPE_LIGHT,
                Sensor.TYPE_PRESSURE,
                Sensor.TYPE_RELATIVE_HUMIDITY -> true
                else -> false
            }
        }
    }

    private fun getPositionSensors(): List<Sensor> {
        return sensorManager.getSensorList(Sensor.TYPE_ALL).filter { sensor ->
            when (sensor.type) {
                Sensor.TYPE_ACCELEROMETER,
                Sensor.TYPE_GYROSCOPE,
                Sensor.TYPE_MAGNETIC_FIELD,
                Sensor.TYPE_ORIENTATION -> true
                else -> false
            }
        }
    }

    private fun getHumanSensors(): List<Sensor> {
        return sensorManager.getSensorList(Sensor.TYPE_ALL).filter { sensor ->
            when (sensor.type) {
                Sensor.TYPE_HEART_RATE,
                Sensor.TYPE_STEP_COUNTER,
                Sensor.TYPE_STEP_DETECTOR -> true
                else -> false
            }
        }
    }
}