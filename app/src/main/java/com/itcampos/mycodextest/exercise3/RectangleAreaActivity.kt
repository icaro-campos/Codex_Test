package com.itcampos.mycodextest.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.itcampos.mycodextest.R
import com.itcampos.mycodextest.databinding.ActivityRectangleAreaBinding
import com.itcampos.mycodextest.exercise2.Rectangle
import com.itcampos.mycodextest.exercise2.RectangleUtils
import java.lang.NumberFormatException

class RectangleAreaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRectangleAreaBinding
    private val rectangleUtils = RectangleUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRectangleAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonCalculateArea.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val inputText = binding.editRectangleAreaCoordinate.text.toString()
        val rectanglesPerLine = inputText.split(";")

        val rectangles = mutableListOf<Rectangle>()

        for (rectText in rectanglesPerLine) {
            val parts = rectText.trim().split(" ")
            if (parts.size == 4) {
                try {
                    val x1 = parts[0].toInt()
                    val y1 = parts[1].toInt()
                    val x2 = parts[2].toInt()
                    val y2 = parts[3].toInt()

                    rectangles.add(Rectangle(x1, y1, x2, y2))
                } catch (e: NumberFormatException) {
                    binding.textResultArea.text = "Formato de entrada inválido"
                }
            }
        }

        val resultAB = rectangleUtils.intersectionArea(rectangles[0], rectangles[1])
        val resultAC = rectangleUtils.intersectionArea(rectangles[0], rectangles[2])

        binding.textResultArea.text =
            "areaOfIntersection(A, B) => $resultAB \nareaOfIntersection(A, C) => $resultAC"
    }
}