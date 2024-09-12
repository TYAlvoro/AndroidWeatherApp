package com.example.weatherapp.api.repository

import com.example.weatherapp.api.model.WeatherResponse
import com.example.weatherapp.api.network.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getWeather() {
    val city = "Москва"
    val apiKey = "ed9166e9dc883b7cb80ffad49337ac92"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi = retrofit.create(OpenWeatherApi::class.java)
    val call = weatherApi.getCurrentWeather(city, apiKey)

    call.enqueue(object : Callback<WeatherResponse> {
        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful) {
                val weather = response.body()
                println("Город: ${weather?.name}")
                println("Температура: ${weather?.main?.temp}")
            } else {
                println(response.code())
            }
        }

        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            println(t.message)
        }
    })
}