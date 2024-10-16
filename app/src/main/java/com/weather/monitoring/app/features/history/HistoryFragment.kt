package com.weather.monitoring.app.features.history

import com.weather.monitoring.app.R
import com.weather.monitoring.app.base.BaseFragment
import com.weather.monitoring.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHomeBinding>(){
    override fun resId(): Int {
        return R.layout.fragment_history
    }
}