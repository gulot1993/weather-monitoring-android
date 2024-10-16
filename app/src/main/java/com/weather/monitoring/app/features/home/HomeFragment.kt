package com.weather.monitoring.app.features.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun resId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }


    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .uiState
                    .collectLatest { state ->
                        observeUIState(state)
                    }
            }
        }
    }

    private fun observeUIState(state: HomeUIState) {
        binding?.apply {
            loadingIndicator.isVisible = state.isLoading

            if (state.error.isNotEmpty()) {
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
            }

            val weather = state.weather
            tvLocation.text = weather.location
            tvSunset.text = getString(R.string.sunset, weather.sunset)
            tvSunrise.text = getString(R.string.sunrise, weather.sunrise)
            tvTemperature.text = getString(R.string.temperature, weather.temperature.toString())
            tvDescription.text = weather.description
            ivWeather.setImageDrawable(ContextCompat.getDrawable(requireContext(), weatherImage(weather)))
            tvDay.text = weather.dayOfWeek
        }
    }

    @DrawableRes
    private fun weatherImage(weatherForecast: WeatherForecast): Int {
        val imageDrawable = when(weatherForecast.condition) {
            WeatherForecast.Companion.WeatherConditionTime.RAINY_NIGHT -> R.drawable.ic_rainy_night
            WeatherForecast.Companion.WeatherConditionTime.RAINY_DAY -> R.drawable.ic_rainy_day
            WeatherForecast.Companion.WeatherConditionTime.CLOUDY_DAY -> R.drawable.ic_cloudy_day
            WeatherForecast.Companion.WeatherConditionTime.CLOUDY_NIGHT -> R.drawable.ic_cloudy_night
        }
        return imageDrawable
    }
}