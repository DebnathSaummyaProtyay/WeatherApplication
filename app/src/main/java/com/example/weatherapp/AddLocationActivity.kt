package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class AddLocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        val locationInput = findViewById<EditText>(R.id.locationInput)
        val saveLocationButton = findViewById<Button>(R.id.saveLocationButton)

        saveLocationButton.setOnClickListener {
            val locationName = locationInput.text.toString()
            // Logic to save the location
        }
    }
}
