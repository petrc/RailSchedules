package com.example.irelandtrainapp.dtos

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root (name = "ArrayOfObjTrainPositions", strict = false)
class TrainsResponseDTO @JvmOverloads constructor(

    @field:ElementList(name = "objTrainPositions", inline=true)
    var trainsList: List<TrainDTO> = mutableListOf()
)