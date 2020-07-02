package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.irelandtrainapp.adapters.StationsAdapter
import com.example.irelandtrainapp.dtos.StationDTO
import java.util.*

class StationsViewModel : BaseViewModel() {

    var stations = MutableLiveData<List<StationDTO>>()
    var searchText = MutableLiveData<String>("")
    var stationsAdapter: StationsAdapter? = null

    fun loadStations() {
        if (stations.value == null || isExpired()) {
            loading.value = true
            railRepository.loadStations { resp, err ->
                stationsAdapter?.updateStations(resp)
                stations.value = resp
                error.value = err
                loading.value = false
                lastUpdate = Date()

                if (resp?.size!! > 0) {
                    resultMessage.value = null
                } else {
                    resultMessage.value = "No station information available at this time"
                }
            }
        } else {
            stationsAdapter?.updateStations(stations.value)
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        searchText.value = s.toString()
        if (s.isNotEmpty()) {
            val filteredStations = stations.value?.filter { station ->
                station.description.startsWith(s, true) ||
                        station.code.startsWith(s, true)
            } as MutableList

            filteredStations.addAll(stations.value?.filter { station ->
                station.description.contains(s, true) ||
                        station.code.contains(s, true)
            } as MutableList)

            stationsAdapter?.updateStations(filteredStations.distinct())
        } else {
            stationsAdapter?.updateStations(stations.value)
        }
    }


    fun clearSearch() {
        searchText.value?.let {
            if (it.isEmpty()) searchActive.value = false
        }

        searchText.value = ""
    }
}
