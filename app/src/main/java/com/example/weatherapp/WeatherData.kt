package com.example.weatherapp

data class WeatherData(
    val coordinate: Coordinate,
    val weather: List<WeatherCondition>,
    val base: String,
    val main: MainDetails,
    val visibility: Int,
    val wind: WindDetails,
    val clouds: CloudDetails,
    val dt: Long,
    val sys: SystemDetails,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
) {
    data class Coordinate(val lon: Double, val lat: Double)
    data class WeatherCondition(val id: Int, val main: String, val description: String, val icon: String)
    data class MainDetails(
        val temp: Double,
        val feels_like: Double,
        val temp_min: Double,
        val temp_max: Double,
        val pressure: Int,
        val humidity: Int,
        val sea_level: Int,
        val grnd_level: Int
    )

    data class WindDetails(val speed: Double, val deg: Int, val gust: Double)
    data class CloudDetails(val all: Int)
    data class SystemDetails(val type: Int, val id: Int, val country: String, val sunrise: Long, val sunset: Long)
}
