package com.weather.monitoring.app.features.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseActivity
import com.weather.monitoring.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
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
//            bottomNavView.isVisible = destination.id == R.id.homeFragment || destination.id == R.id.historyFragment
        }
    }
}