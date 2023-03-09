package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import layout.Question

private const val TAG = "mainActivity" // why do we add it before the main class def?
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentIndex: Int = 0
    private var answered: Boolean = false
    private var numCorrect: Int = 0 // Challenge 3.2: Give a percentage at end of quiz

    private val questionBank = listOf(
        Question(R.string.q_australia, true),
        Question(R.string.q_oceans, true),
        Question(R.string.q_africa, true),
        Question(R.string.q_mideast, true),
        Question(R.string.q_asia, false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        // setContentView(R.layout.activity_main) // inflates activity_main view, instantiates vars defined there
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Setup UI functions */
        binding.trueButton.setOnClickListener { view: View ->
            if (!answered){checkAnswer(true)}
            else { Toast.makeText(this, R.string.answered_text, Toast.LENGTH_SHORT).show() }
        }

        binding.falseButton.setOnClickListener {
            if (!answered){checkAnswer(false)}
            else { Toast.makeText(this, R.string.answered_text, Toast.LENGTH_SHORT).show() }
        }

        binding.nextButton.setOnClickListener {
            if((currentIndex + 1) >= questionBank.size) {
                // Challenge 3.2: do end of quiz stuff
                // append correct % to string
                val resultsString = getString(R.string.end_of_quiz) +  "\nYour result: $numCorrect correct out of ${questionBank.size}"
                Toast.makeText(this, resultsString, Toast.LENGTH_SHORT).show() // C3.2 temp toast; next steps make it a text view and disable next.
                // this.addContentView(new TextView())
            }
            else {
                currentIndex++ // Challenge 3.2: no wrap
                updateQuestion()
            }
        }

        /* Initializing app */
        updateQuestion()

    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    /* helper functions */
    private fun updateQuestion(){
        val questionTextResId =  questionBank[currentIndex].textResId // get reference
        answered = false; // reset `answered` state
        Log.d(TAG, "Updating question text and answered var: $currentIndex, $answered")
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val currAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == currAnswer){
            R.string.correct_toast
        }
        else { R.string.incorrect_toast }

        if (userAnswer == currAnswer) numCorrect++ // C3.2

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        // Challenge 3.1: Preventing Repeat Answers
        // set state to `answered`
        answered = true
    }
}