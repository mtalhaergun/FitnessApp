package com.mte.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mte.fitnessapp.databinding.ActivityLoginBinding
import com.mte.fitnessapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signupBtn.setOnClickListener {

        }

        binding.alreadyHave.setOnClickListener {
            finish()
        }
    }
}