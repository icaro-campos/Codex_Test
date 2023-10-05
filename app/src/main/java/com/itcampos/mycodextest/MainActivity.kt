package com.itcampos.mycodextest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.itcampos.mycodextest.databinding.ActivityMainBinding
import com.itcampos.mycodextest.exercise1.CnpjValidationActivity
import com.itcampos.mycodextest.exercise2.RectangleIntersectActivity
import com.itcampos.mycodextest.exercise3.RectangleAreaActivity
import com.itcampos.mycodextest.exercise4.ToDoListActivity
import com.itcampos.mycodextest.exercise5.WorldClockActivity
import com.itcampos.mycodextest.exercise6.WorldClockRestServerActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
        binding.button5.setOnClickListener(this)
        binding.button6.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.button_1 -> {
                startActivity(Intent(this, CnpjValidationActivity::class.java))
            }
            R.id.button_2 -> {
                startActivity(Intent(this, RectangleIntersectActivity::class.java))
            }
            R.id.button_3 -> {
                startActivity(Intent(this, RectangleAreaActivity::class.java))
            }
            R.id.button_4 -> {
                startActivity(Intent(this, ToDoListActivity::class.java))
            }
            R.id.button_5 -> {
                startActivity(Intent(this, WorldClockActivity::class.java))
            }
            R.id.button_6 -> {
                startActivity(Intent(this, WorldClockRestServerActivity::class.java))
            }
        }
    }
}