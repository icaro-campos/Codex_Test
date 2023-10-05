package com.itcampos.mycodextest.exercise5

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itcampos.mycodextest.databinding.ActivityWorldClockBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorldClockActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWorldClockBinding
    private val networkService = NetworkService()
    private val jsonParser = JsonParser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorldClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonQuerie.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        binding.textSearching.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val maxAttempts = 2
            val retryIntervalMillis: Long = 3000
            var currentAttempt = 0

            while (currentAttempt < maxAttempts) {
                try {
                    val worldClockDataJson =
                        networkService.fetchWorldClockData("http://worldclockapi.com/api/json/utc/now")
                    val worldClockData = jsonParser.parseWorldClockData(worldClockDataJson)

                    runOnUiThread {
                        if (worldClockData != null) {
                            displayWorldClockData(worldClockData)
                        }
                    }

                    currentAttempt++
                    delay(retryIntervalMillis)

                    if (worldClockData != null) {
                        break
                    }

                } catch (e: WorldClockNetworkException) {
                    runOnUiThread {
                        binding.textLocalTimeDate.text = e.message
                    }
                    e.printStackTrace()
                } catch (e: Exception) {
                    runOnUiThread {
                        binding.textLocalTimeDate.text = e.message
                    }
                    e.printStackTrace()
                }

                if (currentAttempt == maxAttempts) {
                    break
                }
            }
            runOnUiThread {
                binding.textSearching.visibility = View.GONE
            }

        }
    }

    private fun displayWorldClockData(data: WorldClockData?) {
        if (data != null) {
            binding.textLocalTimeDate.text = "Hora Local: ${data.localTime}"
            binding.textUtcTimeDate.text = "Hora UTC: ${data.utcTime}"
        }
    }
}