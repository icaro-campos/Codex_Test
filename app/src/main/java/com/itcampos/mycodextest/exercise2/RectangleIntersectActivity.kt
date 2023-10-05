package com.itcampos.mycodextest.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.itcampos.mycodextest.R
import com.itcampos.mycodextest.databinding.ActivityRectangleIntersectBinding
import java.lang.NumberFormatException

class RectangleIntersectActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRectangleIntersectBinding
    private val rectangleUtils = RectangleUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRectangleIntersectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val inputText = binding.editRectangleCoordinate.text.toString()
        val rectanglesPerLine = inputText.split(";")

        val rectangles = mutableListOf<Rectangle>()

        for (rectText in rectanglesPerLine) {
            val parts = rectText.trim().split("\\s+".toRegex())
            if (parts.size >= 4) {
                try {
                    val x1 = parts[0].toInt()
                    val y1 = parts[1].toInt()
                    val x2 = parts[2].toInt()
                    val y2 = parts[3].toInt()

                    rectangles.add(Rectangle(x1, y1, x2, y2))
                } catch (e: NumberFormatException) {
                    binding.textResult.text = "Formato de entrada inválido"
                }
            }
        }

        val resultAB = rectangleUtils.intersects(rectangles[0], rectangles[1])
        val resultAC = rectangleUtils.intersects(rectangles[0], rectangles[2])
        val resultBC = rectangleUtils.intersects(rectangles[1], rectangles[2])

        binding.textResult.text =
            "intersects(A, B) => $resultAB \nintersects(A, C) => $resultAC \nintersects(B, C) => $resultBC"
    }
}
