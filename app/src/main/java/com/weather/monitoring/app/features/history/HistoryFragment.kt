package com.weather.monitoring.app.features.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.databinding.FragmentHistoryBinding
import com.weather.monitoring.app.features.history.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private var adapter: HistoryAdapter? = null
    private val viewModel: HistoryViewModel by viewModels()

    override fun resId(): Int {
        return R.layout.fragment_history
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        adapter = HistoryAdapter()
        binding!!.rvWeathers.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.getHistoryWeatherForecast()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collectLatest {
                        observeUIState(it)
                    }
            }
        }
    }

    private fun observeUIState(state: HistoryUIState) {
        binding?.apply {
            loadingIndicator.isVisible = state.isLoading

            if (state.error.isNotEmpty()) {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            adapter?.submitList(state.weatherForecasts)
        }
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}