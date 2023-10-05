package com.itcampos.mycodextest.exercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.itcampos.mycodextest.R
import com.itcampos.mycodextest.databinding.ActivityCnpjValidationBinding

class CnpjValidationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCnpjValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCnpjValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonValidate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val inputCnpj = binding.editCnpj.text.toString()
        val isValid = CNPJValidator.isValidCNPJ(inputCnpj)

        if (isValid) {
            binding.textValidated.text = getString(R.string.cnpj_is_valid)
        } else {
            binding.textValidated.text = getString(R.string.cnpj_is_invalid)
        }
    }
}