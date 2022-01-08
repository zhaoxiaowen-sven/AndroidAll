package com.sven.androidall.weather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status:String, val places:List<Place>, @SerializedName("formatted_address") val address: String)