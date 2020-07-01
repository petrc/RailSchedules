package com.example.irelandtrainapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.irelandtrainapp.repositories.RailRepository
import java.util.*

open class BaseViewModel : ViewModel() {
    protected val railRepository = RailRepository.instance
    protected var lastUpdate: Date? = null
    var loading = MutableLiveData<Boolean>()
    var resultMessage = MutableLiveData<String>()
    var error = MutableLiveData<String>()

    fun isExpired(): Boolean {
        // if last update older than 1 minute
        return lastUpdate == null || Date().time - lastUpdate!!.time > 60000
    }
}
