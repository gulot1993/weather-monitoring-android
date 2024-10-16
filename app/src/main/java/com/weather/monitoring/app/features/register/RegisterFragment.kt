package com.weather.monitoring.app.features.register

import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun resId(): Int {
        return R.layout.fragment_register
    }
}