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
import com.example.irelandtrainapp.adapters.TrainsAdapter
import com.example.irelandtrainapp.databinding.TrainsFragmentBinding
import com.example.irelandtrainapp.viewmodels.TrainsViewModel

class TrainsFragment : Fragment() {

    private lateinit var trainsViewModel: TrainsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = TrainsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        trainsViewModel = ViewModelProvider(this).get(TrainsViewModel::class.java)

        trainsViewModel.trainsAdapter = TrainsAdapter { train ->
            val bundle = bundleOf("trainCode" to train.code, "trainDate" to train.date)
            view?.findNavController()
                ?.navigate(R.id.action_trainsFragment_to_trainSchedulesFragment, bundle)
        }

        trainsViewModel.loadTrains()

        binding.viewModel = trainsViewModel

        binding.recyclerViewTrains.apply {
            adapter = trainsViewModel.trainsAdapter
        }

        return binding.root
    }
}
