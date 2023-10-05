package com.itcampos.mycodextest.exercise5

import org.json.JSONObject

class JsonParser {
    fun parseWorldClockData(jsonString: String?): WorldClockData? {
        try {
            val jsobObject = JSONObject(jsonString)
            val localTime = jsobObject.optString("currentDateTime")
            val utcTime = jsobObject.optString("utcOffset")
            return WorldClockData(localTime, utcTime)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}