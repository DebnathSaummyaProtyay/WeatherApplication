package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LocationWeatherActivity : AppCompatActivity() {

    // Variables for the UI elements
    private lateinit var locationSpinner: Spinner
    private lateinit var locationName: TextView
    private lateinit var maxMinTemp: TextView
    private lateinit var airQualityValue: TextView
    private lateinit var sunriseTime: TextView
    private lateinit var sunsetTime: TextView
    private lateinit var uvIndexValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        // Bind views to variables
        locationSpinner = findViewById(R.id.locationSpinner)
        locationName = findViewById(R.id.locationName)
        maxMinTemp = findViewById(R.id.maxMinTemp)
        airQualityValue = findViewById(R.id.airQualityValue)
        sunriseTime = findViewById(R.id.sunriseTime)
        sunsetTime = findViewById(R.id.sunsetTime)
        uvIndexValue = findViewById(R.id.uvIndexValue)

        // Dummy data
        val locations = listOf("North America", "Europe", "Asia")
        val weatherData = mapOf(
            "North America" to WeatherData("24°", "18°", "Air Quality: 3-Low Health Risk", "Sunrise:5:28 AM", "Sunset:7:25 PM", "UV:4 Moderate"),
            "Europe" to WeatherData("22°", "15°", "Air Quality2-Moderate Health Risk", "Sunrise6:00 AM", "Sunset:8:00 PM", "UV:5 High"),
            "Asia" to WeatherData("30°", "25°", "Air Quality4-High Health Risk", "Sunrise5:00 AM", "Sunset:7:00 PM", "UV:6 Very High")
        )

        // Set up spinner with locations
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = adapter

        // Set an item selected listener on the spinner
        locationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLocation = locations[position]
                val data = weatherData[selectedLocation]

                // Update UI elements with the weather data for the selected location
                locationName.text = selectedLocation
                maxMinTemp.text = "Max: ${data?.maxTemp} Min: ${data?.minTemp}"
                airQualityValue.text = data?.airQuality
                sunriseTime.text = data?.sunrise
                sunsetTime.text = data?.sunset
                uvIndexValue.text = data?.uvIndex
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle case when nothing is selected if needed
            }
        }

    }
}

data class WeatherData(
    val maxTemp: String,
    val minTemp: String,
    val airQuality: String,
    val sunrise: String,
    val sunset: String,
    val uvIndex: String
)

