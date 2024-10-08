package com.example.weatherapp.api.network

import com.example.weatherapp.api.model.CityResponse
import com.example.weatherapp.api.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): Call<WeatherResponse>

    @GET("direct")
    fun getCurrentCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Call<List<CityResponse>>
}
