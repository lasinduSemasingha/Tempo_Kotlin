package com.example.tempo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tempo.databinding.ActivityLoginBinding
import com.example.tempo.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.registerButton.setOnClickListener{
            val registerFirstname = binding.fName.text.toString()
            val registerLastname = binding.lName.text.toString()
            val registerAge = binding.age.text.toString().toIntOrNull() ?: 0
            val registerEmail = binding.email.text.toString()
            val registerUsername = binding.username.text.toString()
            val registerPassword = binding.password.text.toString()
            registerDatabase(registerFirstname, registerLastname, registerAge,registerEmail, registerUsername, registerPassword)
        }
        binding.lRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerDatabase(firstName: String, lastName: String,age: Int, email: String, username: String, password: String){
        val insertedRowId = databaseHelper.insertUser(firstName,lastName, age, email, username, password)
        if(insertedRowId != -1L) {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
        }
    }
}