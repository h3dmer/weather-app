package com.weather.app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.weather.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity(R.layout.activity_weather) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        navController = findNavController(R.id.activity_weather_nav_host_fragment)
        navController.setGraph(R.navigation.main_nav_graph)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
