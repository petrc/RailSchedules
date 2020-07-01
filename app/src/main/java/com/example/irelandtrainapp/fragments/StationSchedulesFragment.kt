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
import com.example.irelandtrainapp.adapters.StationSchedulesAdapter
import com.example.irelandtrainapp.databinding.StationSchedulesFragmentBinding
import com.example.irelandtrainapp.viewmodels.StationSchedulesViewModel

class StationSchedulesFragment : Fragment() {

    private lateinit var stationsViewModel: StationSchedulesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        stationsViewModel = ViewModelProvider(this).get(StationSchedulesViewModel::class.java)

        stationsViewModel.stationSchedulesAdapter = StationSchedulesAdapter {
            val bundle = bundleOf("trainCode" to it.trainCode, "trainDate" to it.trainDate)
            view?.findNavController()
                ?.navigate(R.id.action_stationSchedulesFragment_to_trainSchedulesFragment, bundle)
        }

        stationsViewModel.loadSchedules(arguments?.getString("stationCode"))

        val binding = StationSchedulesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = stationsViewModel
        binding.recyclerViewSchedules.apply {
            adapter = stationsViewModel.stationSchedulesAdapter
        }

        return binding.root
    }
}
