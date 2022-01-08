package com.sven.androidall.weather.logic.network

import com.sven.androidall.weather.logic.Utils
import com.sven.androidall.weather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${Utils.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String,): Call<PlaceResponse>

}