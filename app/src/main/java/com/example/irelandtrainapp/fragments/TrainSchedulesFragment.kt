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
import com.example.irelandtrainapp.adapters.TrainSchedulesAdapter
import com.example.irelandtrainapp.databinding.TrainSchedulesFragmentBinding
import com.example.irelandtrainapp.viewmodels.TrainSchedulesViewModel

class TrainSchedulesFragment : Fragment() {

    private lateinit var trainSchedluesViewModel: TrainSchedulesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        trainSchedluesViewModel = ViewModelProvider(this).get(TrainSchedulesViewModel::class.java)
        trainSchedluesViewModel.trainSchedulesAdapter = TrainSchedulesAdapter {
            val bundle = bundleOf("stationCode" to it.locationCode)
            view?.findNavController()
                ?.navigate(R.id.action_trainSchedulesFragment_to_stationSchedulesFragment, bundle)
        }

        val trainCode = arguments?.getString("trainCode")
        val trainDate = arguments?.getString("trainDate")

        activity?.actionBar?.let { bar ->
            bar.title = trainCode
        }

        trainSchedluesViewModel.loadSchedules(trainCode, trainDate)

        val binding = TrainSchedulesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = trainSchedluesViewModel
        binding.recyclerViewSchedules.apply {
            adapter = trainSchedluesViewModel.trainSchedulesAdapter
        }

        return binding.root
    }
}

