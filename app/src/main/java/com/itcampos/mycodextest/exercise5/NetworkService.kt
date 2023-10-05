package com.itcampos.mycodextest.exercise5

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class NetworkService {
    fun fetchWorldClockData(urlString: String): String? {
        try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStrem = connection.inputStream
                val reader = BufferedReader(InputStreamReader(inputStrem))
                val response = StringBuilder()
                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                inputStrem.close()
                connection.disconnect()
                return response.toString()
            } else {
                throw WorldClockNetworkException("Solicitação falhou: $responseCode")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw WorldClockNetworkException("Erro de conexão: ${e.message}")
        }
    }
}