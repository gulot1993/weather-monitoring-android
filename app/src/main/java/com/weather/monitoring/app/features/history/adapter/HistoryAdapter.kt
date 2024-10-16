package com.weather.monitoring.app.features.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weather.monitoring.app.R
import com.weather.monitoring.app.data.domain.WeatherForecast
import com.weather.monitoring.app.databinding.ItemHistoryBinding

class HistoryAdapter : ListAdapter<WeatherForecast, HistoryAdapter.HistoryViewHolder>(HistoryItemCallback()) {
    inner class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherForecast) {
            binding.tvDay.text = data.dayOfWeek
            binding.ivWeather.setImageDrawable(ContextCompat.getDrawable(
                binding.root.context,
                weatherImage(data)
            ))
            binding.tvWeather.text = data.description
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(ItemHistoryBinding.bind(view))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HistoryItemCallback : DiffUtil.ItemCallback<WeatherForecast>() {
    override fun areItemsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherForecast, newItem: WeatherForecast): Boolean {
        return oldItem == newItem
    }

}