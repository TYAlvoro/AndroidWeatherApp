package com.example.weatherapp.api.repository

import com.example.weatherapp.api.model.CityResponse
import com.example.weatherapp.api.network.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun GetCity() {
    val city = "Москва"
    val apiKey = "ed9166e9dc883b7cb80ffad49337ac92"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/geo/1.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi = retrofit.create(OpenWeatherApi::class.java)
    val call = weatherApi.GetCity(city, apiKey)

    call.enqueue(object : Callback<CityResponse> {
        override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
            if (response.isSuccessful) {
                val cityResponse = response.body()
                println("Город: ${cityResponse?.name}")
                println("Широта: ${cityResponse?.lat}")
                println("Долгота: ${cityResponse?.lon}")
            } else {
                println(response.code())
            }
        }

        override fun onFailure(call: Call<CityResponse>, t: Throwable) {
            println(t.message)
        }
    })
}