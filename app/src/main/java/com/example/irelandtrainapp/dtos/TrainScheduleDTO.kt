package com.example.irelandtrainapp.dtos

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.example.irelandtrainapp.R
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objTrainMovements", strict = false)
class TrainScheduleDTO @JvmOverloads constructor(
    @field:Element(name = "TrainCode")
    var trainCode: String = "",
    @field:Element(name = "TrainDate", required = false)
    var trainDate: String = "",
    @field:Element(name = "LocationCode")
    var locationCode: String = "",
    @field:Element(name = "LocationFullName", required = false)
    var locationFullName: String = "",
    @field:Element(name = "LocationOrder")
    var locationOrder: String = "",
    @field:Element(name = "LocationType")
    var locationType: String = "",
    @field:Element(name = "TrainOrigin")
    var origin: String = "",
    @field:Element(name = "TrainDestination")
    var destination: String = "",
    @field:Element(name = "ScheduledArrival")
    var schArrival: String = "",
    @field:Element(name = "ScheduledDeparture")
    var schDepart: String = "",
    @field:Element(name = "Arrival", required = false)
    var expArrival: String = "",
    @field:Element(name = "Departure", required = false)
    var expDepart: String = "",
    @field:Element(name = "AutoArrival", required = false)
    var autoArrival: String = "",
    @field:Element(name = "AutoDepart", required = false)
    var autoDepart: String = "",
    @field:Element(name = "StopType")
    var stopType: String = ""
) {
    fun getExpectedArrival(): String {
        return if (expArrival == "00:00:00")
            ""
        else
            expArrival
    }

    fun getExpectedDeparture(): String {
        return if (expDepart == "00:00:00")
            ""
        else
            expDepart
    }

    fun getScheduledArrival(): String {
        return if (schArrival == "00:00:00")
            ""
        else
            schArrival
    }

    fun getScheduledDeparture(): String {
        return if (schDepart == "00:00:00")
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

    fun getLocationImage(): Int {
        return when (locationType) {
            "O" -> R.drawable.ic_origin
            "D" -> R.drawable.ic_destination
            "S" -> R.drawable.ic_stop
            "T" -> R.drawable.ic_time
            else -> 0
        }
    }

    object DataBindingAdapter {
        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, @DrawableRes resId: Int) {
            imageView.setImageResource(resId)
        }
    }
}


