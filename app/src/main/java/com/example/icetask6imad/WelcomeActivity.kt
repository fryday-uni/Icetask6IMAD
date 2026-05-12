package com.example.icetask6imad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.content.Intent
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val welcomeText = findViewById<TextView>(R.id.textView)
        val continueButton = findViewById<Button>(R.id.button)

        welcomeText.text = "welcome, to my quiz! can you get all 6 corrrect?, click the true or false button and press next to submit"

        continueButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}