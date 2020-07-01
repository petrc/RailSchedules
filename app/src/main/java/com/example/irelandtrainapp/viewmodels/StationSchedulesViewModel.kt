package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.irelandtrainapp.adapters.StationSchedulesAdapter
import com.example.irelandtrainapp.dtos.StationScheduleDTO
import com.example.irelandtrainapp.repositories.RailRepository

class StationSchedulesViewModel : ViewModel() {

    val railRepository = RailRepository.instance
    var schedules = MutableLiveData<List<StationScheduleDTO>>()
    var loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    var stationSchedulesAdapter: StationSchedulesAdapter? = null

    fun loadSchedules(code: String?) {
        if (code != null) {
            loading.value = true
            railRepository.loadStationSchedules(code) { resp, err ->
                stationSchedulesAdapter?.updateSchedules(resp)
                schedules.value = resp
                error.value = err
                loading.value = false
            }
        }
    }
}
