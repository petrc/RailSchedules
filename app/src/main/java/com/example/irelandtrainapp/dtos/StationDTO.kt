package com.example.irelandtrainapp.dtos

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "objStation", strict = false)
class StationDTO @JvmOverloads constructor(
    @field:Element(name = "StationDesc")
    var description: String = "",
    @field:Element(name = "StationAlias", required = false)
    var alias: String = "",
    @field:Element(name = "StationLatitude")
    var latitude: Float = 0f,
    @field:Element(name = "StationLongitude")
    var longitude: Float = 0f,
    @field:Element(name = "StationCode")
    var code: String = "",
    @field:Element(name = "StationId")
    var id: String = ""
) {
    var schedules = listOf<StationScheduleDTO>()
}