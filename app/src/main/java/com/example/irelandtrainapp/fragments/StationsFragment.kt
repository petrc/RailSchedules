package com.example.irelandtrainapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.irelandtrainapp.R
import com.example.irelandtrainapp.adapters.StationsAdapter
import com.example.irelandtrainapp.databinding.StationsFragmentBinding
import com.example.irelandtrainapp.viewmodels.StationsViewModel

class StationsFragment : Fragment() {

    private lateinit var stationsViewModel: StationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        stationsViewModel = ViewModelProvider(this).get(StationsViewModel::class.java)

        stationsViewModel.stationsAdapter = StationsAdapter { station ->
            val bundle = bundleOf("stationCode" to station.code)
            view?.findNavController()
                ?.navigate(R.id.action_stationsFragment_to_stationSchedulesFragment, bundle)
        }

        stationsViewModel.loadStations()

        val binding = StationsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = stationsViewModel
        binding.recyclerViewStations.apply {
            adapter = stationsViewModel.stationsAdapter
        }

        return binding.root
    }
}
