package com.example.irelandtrainapp.dtos

import com.example.irelandtrainapp.R
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainPositions", strict = false)
class TrainDTO @JvmOverloads constructor(
    @field:Element(name = "TrainStatus")
    var status: String = "",
    @field:Element(name = "TrainLatitude")
    var latitude: Float = 0f,
    @field:Element(name = "TrainLongitude")
    var longitude: Float = 0f,
    @field:Element(name = "TrainCode")
    var code: String = "",
    @field:Element(name = "TrainDate")
    var date: String = "",
    @field:Element(name = "PublicMessage")
    var message: String = "",
    @field:Element(name = "Direction")
    var direction: String = ""
) {
    fun getMessageParts(): List<String> {
        return message.split("\\n")
    }

    fun getStatusImage(): Int {
        return when (status) {
            "R" -> R.drawable.ic_running
            "N" -> R.drawable.ic_paused
            "T" -> R.drawable.ic_terminated
            else -> 0
        }
    }
}