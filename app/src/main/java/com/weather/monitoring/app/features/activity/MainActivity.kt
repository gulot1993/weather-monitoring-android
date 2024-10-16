package com.weather.monitoring.app.features.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseActivity
import com.weather.monitoring.app.databinding.ActivityMainBinding
import com.weather.monitoring.app.features.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (isRequestLocationPermissionGranted()) {
            getLastKnownLocation()
        } else {
            requestPermissions()
        }
    }

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun resId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupBottomNavView()
    }

    private fun setupBottomNavView() {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navController = findNavController(R.id.navHostFragment)
        bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            bottomNavView.isVisible = destination.id == R.id.homeFragment || destination.id == R.id.historyFragment

            if(destination.id == R.id.homeFragment) {
                initializeRuntimePermissions()
            }
        }
    }

    private fun initializeRuntimePermissions() {
        if (isRequestLocationPermissionGranted()) {
            getLastKnownLocation()
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationProviderClient
            .lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.apply {
                    homeViewModel.getWeatherForecast(
                        lat = latitude,
                        lon = longitude
                    )
                }
            }
    }

    private fun isRequestLocationPermissionGranted(): Boolean {
        val isGranted = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return isGranted
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        )
    }
}