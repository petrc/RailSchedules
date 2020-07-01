package com.example.irelandtrainapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.irelandtrainapp.databinding.MainFragmentBinding
import com.example.irelandtrainapp.viewmodels.MainViewModel

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

}