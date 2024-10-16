package com.weather.monitoring.app.features.register

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
import com.weather.monitoring.app.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    private val viewModel: RegisterViewModel by viewModels()
    override fun resId(): Int {
        return R.layout.fragment_register
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupViewModel()
    }

    private fun setupListener() {
        binding!!.btnSignUp.setOnClickListener {
            viewModel.register(
                binding!!.etUsername.text.toString(),
                binding!!.etPassword.text.toString(),
                binding!!.etFirstName.text.toString(),
                binding!!.etLastName.text.toString()
            )
        }
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collectLatest { state ->
                        observeState(state)
                    }
            }
        }
    }

    private fun observeState(state: RegisterUIState) {
        binding!!.loadingIndicator.isVisible = state.isLoading
        if (state.isSuccess) {
            Toast.makeText(requireContext(), "Saving success", Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }

        if (state.error.isNotEmpty()) {
            Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
        }
    }
}