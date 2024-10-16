package com.weather.monitoring.app.features.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
        binding?.apply {
            val navController = findNavController(R.id.navHostFragment)
            bottomNavView.setupWithNavController(navController)
        }
    }
}