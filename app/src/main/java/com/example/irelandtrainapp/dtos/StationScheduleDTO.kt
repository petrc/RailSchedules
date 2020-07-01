package com.example.irelandtrainapp.dtos

import com.example.irelandtrainapp.R
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import kotlin.math.abs

@Root(name = "objStationData", strict = false)
class StationScheduleDTO @JvmOverloads constructor(
    @field:Element(name = "Traincode")
    var trainCode: String = "",
    @field:Element(name = "Traintype")
    var trainType: String = "",
    @field:Element(name = "Traindate")
    var trainDate: String = "",
    @field:Element(name = "Origin")
    var origin: String = "",
    @field:Element(name = "Destination")
    var destination: String = "",
    @field:Element(name = "Origintime")
    var originTime: String = "",
    @field:Element(name = "Destinationtime")
    var destinationTime: String = "",
    @field:Element(name = "Status", required = false)
    var status: String = "",
    @field:Element(name = "Lastlocation", required = false)
    var lastLocation: String = "",
    @field:Element(name = "Duein")
    var due: String = "",
    @field:Element(name = "Late")
    var late: String = "",
    @field:Element(name = "Exparrival")
    var expArrival: String = "",
    @field:Element(name = "Expdepart")
    var expDepart: String = "",
    @field:Element(name = "Scharrival")
    var schArrival: String = "",
    @field:Element(name = "Schdepart")
    var schDepart: String = "",
    @field:Element(name = "Direction")
    var direction: String = "",
    @field:Element(name = "Locationtype")
    var locationType: String = ""

) {
    fun getLocationImage(): Int {
        return when (locationType) {
            "O" -> R.drawable.ic_origin
            "D" -> R.drawable.ic_destination
            "S" -> R.drawable.ic_stop
            "T" -> R.drawable.ic_time
            else -> 0
        }
    }

    fun getDelayMinutes(): String {
        return abs(late.toInt()).toString()
    }

    fun getDelayText(): String {
        return if (abs(late.toInt()) > 1) {
            "minutes"
        } else {
            "minute"
        } + if (late.toInt() > 0) {
            " late"
        } else {
            " early"
        }
    }

    fun getExpectedArrival(): String {
        return if (expArrival == "00:00")
            ""
        else
            expArrival
    }

    fun getExpectedDeparture(): String {
        return if (expDepart == "00:00")
            ""
        else
            expDepart
    }

    fun getScheduledArrival(): String {
        return if (schArrival == "00:00")
            ""
        else
            schArrival
    }

    fun getScheduledDeparture(): String {
        return if (schDepart == "00:00")
            ""
        else
            schDepart
    }

    fun hasArrivalTimes(): Boolean {
        return getScheduledArrival().isNotEmpty() || getExpectedArrival().isNotEmpty()
    }

    fun hasDepartureTimes(): Boolean {
        return getScheduledDeparture().isNotEmpty() || getExpectedDeparture().isNotEmpty()
    }
}