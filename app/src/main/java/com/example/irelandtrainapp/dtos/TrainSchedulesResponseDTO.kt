package com.example.irelandtrainapp.dtos

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root (name = "ArrayOfObjTrainMovements", strict = false)
class TrainSchedulesResponseDTO @JvmOverloads constructor(

    @field:ElementList(name = "objTrainMovements", inline=true, required = false)
    var trainScheduleList: List<TrainScheduleDTO> = mutableListOf()
)