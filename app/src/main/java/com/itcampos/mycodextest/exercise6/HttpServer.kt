package com.itcampos.mycodextest.exercise6

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class HttpServer {
    private val serverSocket: ServerSocket
    private val gson = Gson()

    init {
        serverSocket = ServerSocket(8080)
    }

    fun startServer() {
        val serverHandler = ServerHandler()
        Thread(serverHandler).start()
    }

    private inner class ServerHandler : Runnable {
        override fun run() {
            while (true) {
                try {
                    val clientSocket = serverSocket.accept()
                    val clientHandler = ClientHandler(clientSocket)
                    Thread(clientHandler).start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private inner class ClientHandler(private val clientSocket: Socket) : Runnable {
        override fun run() {
            try {
                val output = clientSocket.getOutputStream()
                val response = JsonObject()
                val currentDateTime = getCurrentDateTimeInISO8601()
                response.add("currentDateTime", JsonPrimitive(currentDateTime))
                val responseJson = gson.toJson(response)
                val httpResponse =
                    "HTTP/1.1 200 OK\r\nContent-Length: ${responseJson.length}\r\n\r\n$responseJson"
                output.write(httpResponse.toByteArray())
                output.close()
                clientSocket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getCurrentDateTimeInISO8601(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(Date())
    }
}