package com.example.irelandtrainapp.network

import com.example.irelandtrainapp.dtos.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RailApi {

    @GET("getAllStationsXML")
    fun getAllStations(): Call<StationsResponseDTO>

    @GET("getStationDataByCodeXML")
    fun getStationSchedules(@Query("StationCode") stationCode: String): Call<StationSchedulesResponseDTO>

    @GET("getCurrentTrainsXML")
    fun getActiveTrains(): Call<TrainsResponseDTO>

    @GET("getTrainMovementsXML")
    fun getTrainSchedules(@Query("TrainId") trainId: String, @Query("TrainDate") trainDate: String): Call<TrainSchedulesResponseDTO>
}