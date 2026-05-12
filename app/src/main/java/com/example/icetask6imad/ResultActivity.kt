package com.example.icetask6imad

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.forEach
import android.content.Intent

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        // grabs these values from the main activity

        val resultView = findViewById<TextView>(R.id.resultView)


        var scoreComment = ""
        val total = 6

        if (score >= 4) {
            scoreComment = "Genius"
        } //final comments on the score and points gained

        else if (score == 3 ) {
            scoreComment = "Good"
        }

        else {
        scoreComment = "better luck next time"
        }

        resultView.text = "Your score is $score / $total,$scoreComment"

    }
}