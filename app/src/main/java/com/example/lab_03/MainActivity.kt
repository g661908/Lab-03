package com.example.lab_03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve saved email address from SharedPreferences
        val prefs = getSharedPreferences("MyData", MODE_PRIVATE)
        val emailAddress = prefs.getString("LoginName", "")

        Log.d("MainActivity", "onCreate: Retrieved Email - $emailAddress")

        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)

        // Set the retrieved email address in the EditText field, if available
        if (emailAddress != null && !emailAddress.isEmpty()) {
            emailEditText.setText(emailAddress)
        }

        // Create SharedPreferences.Editor to save data
        val editor = prefs.edit()

        // Set up the click listener for the login button
        loginButton.setOnClickListener { view: View? ->
            val email = emailEditText.text.toString()
            // Start the next activity and pass the email address
            val nextPage =
                Intent(
                    this@MainActivity,
                    SecondActivity::class.java
                )
            nextPage.putExtra("EmailAddress", email)

            // Save the email address in SharedPreferences
            editor.putString("LoginName", email)
            editor.apply() // Asynchronously save data

            // Start the second activity
            startActivity(nextPage)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }
}

class SecondActivity {

}
