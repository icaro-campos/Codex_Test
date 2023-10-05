package com.itcampos.mycodextest.exercise2

class RectangleUtils {
    fun intersects(rectangleA: Rectangle, rectangleB: Rectangle): Boolean {

        if (rectangleA.x1 > rectangleB.x2 || rectangleB.x1 > rectangleA.x2 ||
            rectangleA.y1 > rectangleB.y2 || rectangleB.y1 > rectangleA.y2
        ) {

            return false
        }

        return true
    }

    fun intersectionArea(rectangleA: Rectangle, rectangleB: Rectangle): Int {
        val x = maxOf(0, minOf(rectangleA.x2, rectangleB.x2) - maxOf(rectangleA.x1, rectangleB.x1))
        val y = maxOf(0, minOf(rectangleA.y2, rectangleB.y2) - maxOf(rectangleA.y1, rectangleB.y1))

        return x * y
    }
}