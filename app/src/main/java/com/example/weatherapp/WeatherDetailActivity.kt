package com.example.weatherapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class WeatherDetailActivity : AppCompatActivity() {

    private val CHANNEL_ID = "weather_notification_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        //Find bottom navigation icons
        val locationIcon = findViewById<ImageView>(R.id.locationIcon)
        val addLocationIcon = findViewById<ImageView>(R.id.addIcon)
        val menuIcon = findViewById<ImageView>(R.id.menuIcon)

        // Set up click listeners for navigation icons
        locationIcon.setOnClickListener {
            val intent = Intent(this, LocationWeatherActivity::class.java)
            startActivity(intent)
        }

        addLocationIcon.setOnClickListener {
            val intent = Intent(this, AddLocationActivity::class.java)
            startActivity(intent)
        }

        menuIcon.setOnClickListener {
            val intent = Intent(this, MoreOptionsActivity::class.java)
            startActivity(intent)
        }



        // Enable the Up button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Retrieve the temperature data passed from MainActivity
        val temperature = intent.getStringExtra("TEMPERATURE") ?: "N/A"
        findViewById<TextView>(R.id.temperatureText).text = temperature

        // Set up the notification channel (for Android 8.0 and above)
        createNotificationChannel()

        // Send a notification with the temperature data
        sendNotification(temperature)

        // Add the fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WeatherDetailFragment())
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Weather Notifications"
            val descriptionText = "Notifications for weather updates"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(temperature: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_weather)
            .setContentTitle("Weather Update")
            .setContentText("Current temperature: $temperature")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(1001, builder.build())
        }
    }
}
