package com.weather.monitoring.app.features.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun resId(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding!!.tvRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }
}