package com.itcampos.mycodextest.exercise6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.itcampos.mycodextest.R
import com.itcampos.mycodextest.databinding.ActivityWorldClockRestServerBinding
import java.util.concurrent.Executors

class WorldClockRestServerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityWorldClockRestServerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorldClockRestServerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStartServer.setOnClickListener(this)
    }

    override fun onClick(viw: View) {
        val server = HttpServer()
        server.startServer()
    }
}