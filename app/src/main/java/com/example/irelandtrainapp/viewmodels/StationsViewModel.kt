package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.irelandtrainapp.adapters.StationsAdapter
import com.example.irelandtrainapp.dtos.StationDTO
import com.example.irelandtrainapp.repositories.RailRepository

class StationsViewModel : ViewModel() {

    private val railRepository = RailRepository.instance
    var trains = MutableLiveData<List<StationDTO>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()
    var stationsAdapter : StationsAdapter? = null

    fun loadStations() {
        loading.value = true
        railRepository.loadStations { resp, err ->
            stationsAdapter?.updateStations(resp)
            trains.value = resp
            error.value = err
            loading.value = false
        }
    }
}
