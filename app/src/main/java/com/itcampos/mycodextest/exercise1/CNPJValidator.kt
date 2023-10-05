package com.itcampos.mycodextest.exercise1

object CNPJValidator {
    fun isValidCNPJ(cnpj: String): Boolean {
        val cleanedCNPJ = cnpj.replace("\\D".toRegex(), "")

        if (cleanedCNPJ.length != 14) {
            return false
        }

        val firstCheckDigit = calculateCheckDigit(cleanedCNPJ.substring(0, 12))
        val secondCheckDigit = calculateCheckDigit(cleanedCNPJ.substring(0, 13))

        return cleanedCNPJ[12].toString() == firstCheckDigit.toString() &&
                cleanedCNPJ[13].toString() == secondCheckDigit.toString()
    }

    private fun calculateCheckDigit(cnpj: String): Int {
        var sum = 0
        var multiplier = 2

        for (i in cnpj.length - 1 downTo 0) {
            val digit = cnpj[i].toString().toInt()
            sum += digit * multiplier
            multiplier = if (multiplier == 9) 2 else multiplier + 1
        }

        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }
}