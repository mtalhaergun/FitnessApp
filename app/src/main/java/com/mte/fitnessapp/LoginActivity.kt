package com.mte.fitnessapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mte.fitnessapp.databinding.ActivityLoginBinding
import com.mte.fitnessapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth=FirebaseAuth.getInstance()
        // kullanıcı girip yaptığını kontrol ediyorum
        var currentUser=auth.currentUser
        binding.loginBtn.setOnClickListener {
            var girisemail=binding.email.text.toString()
            var girissifre=binding.password.text.toString()
            if(TextUtils.isEmpty(girisemail)){
                binding.email.error="Lütfen email adresinizi giriniz"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(girissifre)){
                binding.password.error="Lütfen şifrenizi giriniz"
                return@setOnClickListener
            }
            //giris bilgilerini doğrulama
            auth.signInWithEmailAndPassword(girisemail,girissifre)
                .addOnCompleteListener(this) {
                    if(it.isSuccessful){
                        val verification = auth.currentUser?.isEmailVerified
                        if(verification==true) {
                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{

                            Toast.makeText(this,"Öncelikle Mail Adresinizi Doğrulamalısınız", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(applicationContext,"Giriş hatalı tekrar deneyiniz", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.registerBtn.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }



}