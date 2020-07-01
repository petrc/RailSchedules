package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.irelandtrainapp.adapters.StationsAdapter
import com.example.irelandtrainapp.dtos.StationDTO
import java.util.*

class StationsViewModel : BaseViewModel() {

    var stations = MutableLiveData<List<StationDTO>>()
    var stationsAdapter: StationsAdapter? = null

    fun loadStations() {
        if (stations.value == null || isExpired()) {
            loading.value = true
            railRepository.loadStations { resp, err ->
                stationsAdapter?.updateStations(resp)
                stations.value = resp
                error.value = err
                loading.value = false

                if(resp?.size!! > 0) {
                    resultMessage.value = null
                }
                else {
                    resultMessage.value = "No station information available at this time"
                }
            }
        } else {
            stationsAdapter?.updateStations(stations.value)
        }
    }
}
