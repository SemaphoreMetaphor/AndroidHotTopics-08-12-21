package com.appnio.androidhottopics.coroutines

import kotlinx.coroutines.delay


class User {
    var token = ""
}

suspend fun login(): User {
    delay(200)
    return User()
}

class Temperature
class Humidity
class WindFactor

data class Weather(val temp: Temperature, val humidity: Humidity, val windFactor: WindFactor)

suspend fun getHumidity(): Humidity {
    delay(100)
    return Humidity()
}

suspend fun getWindFactor(): WindFactor {
    delay(100)
    return WindFactor()
}

