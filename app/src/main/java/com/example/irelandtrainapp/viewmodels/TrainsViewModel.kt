package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.irelandtrainapp.adapters.TrainsAdapter
import com.example.irelandtrainapp.dtos.TrainDTO
import com.example.irelandtrainapp.repositories.RailRepository
import java.util.*

class TrainsViewModel : ViewModel() {

    private val railRepository = RailRepository.instance
    var trains = MutableLiveData<List<TrainDTO>>()
    var loading = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()
    var trainsAdapter: TrainsAdapter? = null
    private var lastUpdate: Date? = null

    fun loadTrains() {
        if (trains.value == null || Date().time - lastUpdate!!.time > 60000) {
            loading.value = true
            railRepository.loadTrains { resp, err ->
                trainsAdapter?.updateTrains(resp)
                trains.value = resp
                error.value = err
                loading.value = false
                lastUpdate = Date()
            }
        } else {
            trainsAdapter?.updateTrains(trains.value)
        }
    }
}
