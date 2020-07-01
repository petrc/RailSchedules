package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.irelandtrainapp.adapters.TrainSchedulesAdapter
import com.example.irelandtrainapp.dtos.TrainScheduleDTO
import java.util.*

class TrainSchedulesViewModel : BaseViewModel() {

    val schedules = MutableLiveData<List<TrainScheduleDTO>>()
    var trainSchedulesAdapter: TrainSchedulesAdapter? = null

    fun loadSchedules(code: String, date: String) {
        if (schedules.value == null || isExpired()) {
            loading.value = true
            railRepository.loadTrainSchedules(code, date) { resp, err ->
                trainSchedulesAdapter?.updateSchedules(resp)
                schedules.value = resp
                error.value = err
                loading.value = false
                lastUpdate = Date()

                if(resp?.size!! > 0) {
                    resultMessage.value = null
                }
                else {
                    resultMessage.value = "No train information at this time"
                }
            }
        } else {
            trainSchedulesAdapter?.updateSchedules(schedules.value)
        }
    }
}
