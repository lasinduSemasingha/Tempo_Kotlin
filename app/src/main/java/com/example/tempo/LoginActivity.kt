package com.example.tempo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tempo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.loginButton.setOnClickListener {
            val loginUsername = binding.usernameEditText.text.toString()
            val loginPassword = binding.passwordEditText.text.toString()
            loginDatabse(loginUsername, loginPassword)
        }
        binding.rRedirect.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabse(username: String, password: String) {
        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
            val firstName = databaseHelper.getFirstName(username)
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }
}
