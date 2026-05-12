package com.example.icetask6imad

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
class MainActivity : AppCompatActivity() {

    data class quizQuestion(
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

        val quizQAndA = arrayOf(
            quizQuestion("Bats are blind.", false),
            quizQuestion("Sound travels faster in water than in air", true),
            quizQuestion("Honey never spoils.", true),
            quizQuestion("Frogs are reptiles", false),
            quizQuestion("Sharks are mammals", false),
            quizQuestion("Goldfish only remember things for three seconds", false),
        ) //this is where we store all the questions and their true answers

                // shuffle random question order
                val remainingQuestions = quizQAndA.indices.shuffled().toMutableList() // still no clue what this does

                // stores current question
                var currentQuestionIndex = 0

                fun showQuestion() {

                    // if all questions are done now
                    if (remainingQuestions.isEmpty()) {

                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("SCORE", points)
                        startActivity(intent)

                        return
                    }

                    // get random unused question
                    currentQuestionIndex = remainingQuestions.removeAt(0)

                    questionView.text = quizQAndA[currentQuestionIndex].question
                    feedbackView.text = ""
                    userAnswer = null
                }

                showQuestion()

                trueButton.setOnClickListener {
                    userAnswer = true
                }

                falseButton.setOnClickListener {
                    userAnswer = false
                }

                nextButton.setOnClickListener {

                    if (userAnswer == null) {
                        feedbackView.text = "Pick true or false first!"
                        return@setOnClickListener
                    }

                    // check answer
                    if (quizQAndA[currentQuestionIndex].correctAnswer == userAnswer) {
                        points++
                        feedbackView.text = "Nicely done!"
                    } else {
                        feedbackView.text = "Oof"
                    }

                    // delay before next question
                    questionView.postDelayed({

                        showQuestion()

                    }, 1500)
                }
            }
        }