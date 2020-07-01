package com.example.irelandtrainapp.dtos

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root (name = "ArrayOfObjStation", strict = false)
class StationsResponseDTO @JvmOverloads constructor(
    @field:ElementList(name = "objStation", inline=true)
    var stationList: List<StationDTO> = mutableListOf()
)