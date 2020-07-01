package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.irelandtrainapp.adapters.TrainSchedulesAdapter
import com.example.irelandtrainapp.dtos.TrainDTO
import com.example.irelandtrainapp.dtos.TrainScheduleDTO
import com.example.irelandtrainapp.repositories.RailRepository
import java.util.*

class TrainSchedulesViewModel : ViewModel() {

    private val railRepository = RailRepository.instance
    val train = MutableLiveData<TrainDTO>()
    val schedules = MutableLiveData<List<TrainScheduleDTO>>()
    var loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    var trainSchedulesAdapter: TrainSchedulesAdapter? = null
    private var lastUpdate: Date? = null

    fun loadSchedules(code: String?, date: String?) {
        if (schedules.value == null || Date().time - lastUpdate!!.time > 60000) {
            if (code != null && date != null) {
                train.value = railRepository.getTrain(code)
                loading.value = true
                railRepository.loadTrainSchedules(code, date) { resp, err ->
                    trainSchedulesAdapter?.updateSchedules(resp)
                    schedules.value = resp
                    error.value = err
                    loading.value = false
                    lastUpdate = Date()
                }
            }
        }
        else {
            trainSchedulesAdapter?.updateSchedules(schedules.value)
        }
    }
}
