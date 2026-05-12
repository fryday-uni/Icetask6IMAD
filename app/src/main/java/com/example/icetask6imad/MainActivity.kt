package com.example.icetask6imad

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
class MainActivity : AppCompatActivity() {

    data class HackQuestion(
        val question: String,
        val correctAnswer: Boolean
    )  // I find this kinda interesting

    // initializing and declaring
    var i = 0
    var points = 0
    var userAnswer: Boolean? = null // set to null so we don't have non answers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // plugging in the buttons and views
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        val questionView = findViewById<TextView>(R.id.questionView)
        val feedbackView = findViewById<TextView>(R.id.feedbackView)

        val hackQuestion = arrayOf(
            HackQuestion("Lightning never strikes the same place twice.", false),
            HackQuestion("Humans only use 10% of their brains.", false),
            HackQuestion("Bananas are naturally radioactive.", true),
            HackQuestion("There are more possible games of chess than atoms in the observable universe.", true),
            HackQuestion("Water can boil and freeze at the same time", true)
        ) //this is where we store all the questions and their true answers

        fun showQuestion() {
            questionView.text = hackQuestion[i].question
            feedbackView.text = ""
            userAnswer = null
        }  // function so I can grab it later

        showQuestion()

        trueButton.setOnClickListener {
            userAnswer = true
        }

        falseButton.setOnClickListener {
            userAnswer = false
        }

        nextButton.setOnClickListener {

            if (userAnswer == null)  {
                feedbackView.text = "Pick true or false first!"
                return@setOnClickListener  // cuts off the code here, stops weird errors, if there isn't a button pressed it sends a message
            }

            if (hackQuestion[i].correctAnswer == userAnswer) { //if the current questions answer is correct it does this
                points++ //adds 1 to the points
                feedbackView.text = "Nicely done!"
            } else {
                feedbackView.text = "WRONG!!!"
            }

            questionView.postDelayed({
                i++  //adds to index, changes question basically


                // this takes us to the next question pretty much + delay

                if (i < hackQuestion.size) {
                    showQuestion()  //more flexible way of cutting it off
                } else {

                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("SCORE", points)
                    //sends these values on over to the results page
                    startActivity(intent) //sends us to the next activity
                } }, 1500) //delays so we actually can see what feedback it is
        }
    }
}