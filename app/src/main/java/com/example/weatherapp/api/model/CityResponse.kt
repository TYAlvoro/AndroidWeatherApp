package com.example.weatherapp.api.model

data class CityResponse (
    val name : String,
    val localNames: Map<String, String>,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
)