package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.irelandtrainapp.adapters.TrainsAdapter
import com.example.irelandtrainapp.dtos.TrainDTO
import java.util.*

class TrainsViewModel : BaseViewModel() {

    var trains = MutableLiveData<List<TrainDTO>>()
    var trainsAdapter: TrainsAdapter? = null

    fun loadTrains() {
        if (trains.value == null || isExpired()) {
            loading.value = true
            railRepository.loadTrains { resp, err ->
                trainsAdapter?.updateTrains(resp)
                trains.value = resp
                error.value = err
                loading.value = false
                lastUpdate = Date()

                if(resp?.size!! > 0) {
                    resultMessage.value = null
                }
                else {
                    resultMessage.value = "No trains scheduled at this time"
                }
            }
        } else {
            trainsAdapter?.updateTrains(trains.value)
        }
    }
}
