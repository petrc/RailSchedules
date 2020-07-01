package com.example.irelandtrainapp.repositories

import com.example.irelandtrainapp.dtos.*
import com.example.irelandtrainapp.network.RailApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RailRepository {

    private val railApi: RailApi
    private val trains = mapOf<String, TrainDTO>()

    companion object {
        val instance = RailRepository()
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.irishrail.ie/realtime/realtime.asmx/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
        railApi = retrofit.create(RailApi::class.java)
    }

    fun loadStations(onResult: (response: List<StationDTO>?, error: String?) -> Unit) {
        railApi.getAllStations().enqueue(object : Callback<StationsResponseDTO> {
            override fun onResponse(
                call: Call<StationsResponseDTO>?,
                response: Response<StationsResponseDTO>?
            ) {
                if (response?.isSuccessful!! && response.body()?.stationList != null) {
                    onResult(response.body()?.stationList, null)
                } else {
                    onResult(null, response.message())
                }
            }
            override fun onFailure(call: Call<StationsResponseDTO>?, t: Throwable?) {
                onResult(null, t?.localizedMessage)
            }
        })
    }

    fun loadStationSchedules(
        code: String,
        onResult: (response: List<StationScheduleDTO>?, error: String?) -> Unit
    ) {

        railApi.getStationSchedules(code)
            .enqueue(object : Callback<StationSchedulesResponseDTO> {
                override fun onResponse(
                    call: Call<StationSchedulesResponseDTO>?,
                    response: Response<StationSchedulesResponseDTO>?
                ) {
                    if (response?.isSuccessful!! && response.body()?.stationScheduleList != null) {
                        onResult(response.body()?.stationScheduleList, null)
                    } else {
                        onResult(null, response.message())
                    }
                }
                override fun onFailure(call: Call<StationSchedulesResponseDTO>?, t: Throwable?) {
                    onResult(null, t?.localizedMessage)
                }
            })
    }

    fun loadTrains(onResult: (response: List<TrainDTO>?, error: String?) -> Unit) {
        railApi.getActiveTrains().enqueue(object : Callback<TrainsResponseDTO> {

            override fun onResponse(
                call: Call<TrainsResponseDTO>?,
                response: Response<TrainsResponseDTO>?
            ) {
                if (response?.isSuccessful!! && response.body()?.trainsList != null) {
                    onResult(response.body()?.trainsList, null)
                } else {
                    onResult(null, response.message())
                }
            }
            override fun onFailure(call: Call<TrainsResponseDTO>?, t: Throwable?) {
                onResult(null, t?.localizedMessage)
            }
        })
    }

    fun loadTrainSchedules(
        code: String,
        date: String,
        onResult: (response: List<TrainScheduleDTO>?, error: String?) -> Unit
    ) {
        railApi.getTrainSchedules(code, date).enqueue(object : Callback<TrainSchedulesResponseDTO> {

            override fun onResponse(
                call: Call<TrainSchedulesResponseDTO>?,
                response: Response<TrainSchedulesResponseDTO>?
            ) {
                if (response?.isSuccessful!! && response.body()?.trainScheduleList != null) {
                    onResult(response.body()?.trainScheduleList, null)
                } else {
                    onResult(null, response.message())
                }
            }
            override fun onFailure(call: Call<TrainSchedulesResponseDTO>?, t: Throwable?) {
                onResult(null, t?.localizedMessage)
            }
        })
    }

    fun getTrain(code: String) : TrainDTO? {
        return trains[code]
    }

}