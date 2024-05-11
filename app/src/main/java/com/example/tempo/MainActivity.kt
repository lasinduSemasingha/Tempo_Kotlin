package com.example.tempo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        // Retrieve username from Intent extras
        val username = intent.getStringExtra("USERNAME")

        // Retrieve first name from the database using the username
        val firstName = getFirstNameFromDatabase(username)

        // Update the TextView with the retrieved first name
        val firstNameTextView = findViewById<TextView>(R.id.firstNameTextView)
        firstNameTextView.text = "Hi, $firstName" // Add "Hi, " before the retrieved first name

        val availableCount = 10
        val completedCount = 5

        findViewById<TextView>(R.id.availableTextView).text = "Available: $availableCount"
        findViewById<TextView>(R.id.completedTextView).text = "Completed: $completedCount"
    }

    private fun getFirstNameFromDatabase(username: String?): String {
        // Check if username is not null
        if (username != null) {
            // Assuming you have a method in DatabaseHelper to get the first name
            // For example, if you have a method named getFirstName(), use it here
            // Replace this with your actual implementation
            return databaseHelper.getFirstName(username)
        } else {
            return "" // Handle the case where username is null
        }
    }

}
