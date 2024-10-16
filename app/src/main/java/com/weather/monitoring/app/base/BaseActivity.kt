package com.weather.monitoring.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B: ViewDataBinding> : AppCompatActivity() {
    var binding: B? = null

    @LayoutRes
    abstract fun resId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil
            .setContentView(this, resId())
        binding?.lifecycleOwner = this
    }

    override fun onDestroy() {
        binding?.unbind()
        binding = null
        super.onDestroy()
    }
}