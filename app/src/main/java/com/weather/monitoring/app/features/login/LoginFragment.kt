package com.weather.monitoring.app.features.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()
    override fun resId(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupViewModel()
    }

    private fun setupListener() {
        binding!!.tvRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding!!.btnSignIn.setOnClickListener {
            viewModel.login(binding!!.etUsername.text.toString(), binding!!.etPassword.text.toString())
        }
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collectLatest {
                        observeState(it)
                    }
            }
        }
    }

    private fun observeState(state: LoginUIState) {
        binding!!.loadingIndicator.isVisible = state.isLoading
        if (state.isSuccess) {
            Toast.makeText(requireContext(), "Login success", Toast.LENGTH_LONG).show()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            return
        }

        if (state.error.isNotEmpty()) {
            Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
        }
    }
}