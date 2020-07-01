package com.example.irelandtrainapp.dtos

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root (name = "ArrayOfObjStationData", strict = false)
class StationSchedulesResponseDTO @JvmOverloads constructor(

    @field:ElementList(name = "objStationData", inline=true, required = false)
    var stationScheduleList: List<StationScheduleDTO> = mutableListOf()
)