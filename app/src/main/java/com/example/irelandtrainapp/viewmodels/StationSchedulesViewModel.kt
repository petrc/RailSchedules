package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.irelandtrainapp.adapters.StationSchedulesAdapter
import com.example.irelandtrainapp.dtos.StationScheduleDTO
import java.util.*

class StationSchedulesViewModel : BaseViewModel() {

    var schedules = MutableLiveData<List<StationScheduleDTO>>()
    var stationSchedulesAdapter: StationSchedulesAdapter? = null

    fun loadSchedules(code: String) {
        if (schedules.value == null || isExpired()) {
            loading.value = true
            railRepository.loadStationSchedules(code) { resp, err ->
                stationSchedulesAdapter?.updateSchedules(resp)
                schedules.value = resp
                error.value = err
                loading.value = false

                if(resp?.size!! > 0) {
                    resultMessage.value = null
                }
                else {
                    resultMessage.value = "No trains scheduled at this time"
                }
            }
        } else {
            stationSchedulesAdapter?.updateSchedules(schedules.value)
        }
    }
}
