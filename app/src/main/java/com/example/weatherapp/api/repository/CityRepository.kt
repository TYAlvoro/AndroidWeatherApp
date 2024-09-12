package com.example.weatherapp.api.repository

import com.example.weatherapp.api.model.CityResponse
import com.example.weatherapp.api.network.OpenWeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getCity() {
    val city = "Москва"
    val apiKey = "ed9166e9dc883b7cb80ffad49337ac92"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/geo/1.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi = retrofit.create(OpenWeatherApi::class.java)
    val call = weatherApi.getCurrentCity(city, apiKey)

    call.enqueue(object : Callback<List<CityResponse>> {
        override fun onResponse(call: Call<List<CityResponse>>, response: Response<List<CityResponse>>) {
            if (response.isSuccessful) {
                val cityList = response.body()
                if (!cityList.isNullOrEmpty()) {
                    val cityResponse = cityList[0]
                    println("Город: ${cityResponse.name}")
                    println("Широта: ${cityResponse.lat}")
                    println("Долгота: ${cityResponse.lon}")
                } else {
                    println("Город не найден")
                }
            } else {
                println("Ошибка: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<CityResponse>>, t: Throwable) {
            println("Ошибка: ${t.message}")
        }
    })
}
