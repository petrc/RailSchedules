package com.example.irelandtrainapp.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.irelandtrainapp.R

class MainViewModel : ViewModel() {

    val title = MutableLiveData<String>()
    val subTitle = MutableLiveData<String>()

    fun onClick(view: View) {
        when (view.id) {
            R.id.buttonStations -> view.findNavController().navigate(R.id.action_mainFragment_to_stationsFragment)
            R.id.buttonTrains -> view.findNavController().navigate(R.id.action_mainFragment_to_trainsFragment)
        }
    }
}