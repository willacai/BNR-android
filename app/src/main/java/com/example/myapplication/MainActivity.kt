package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import layout.Question

private const val TAG = "mainActivity" // why do we add it before the main class def?
class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")

        // setContentView(R.layout.activity_main) // inflates activity_main view, instantiates vars defined there
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Got a QuizViewModel: $quizViewModel") // simult. initialized and logged; "scoped to activity"

        /* Setup UI functions */
        binding.trueButton.setOnClickListener { view: View ->
            if (!quizViewModel.answered){checkAnswer(true)}
            else { Toast.makeText(this, R.string.answered_text, Toast.LENGTH_SHORT).show() }
        }

        binding.falseButton.setOnClickListener {
            if (!quizViewModel.answered){checkAnswer(false)}
            else { Toast.makeText(this, R.string.answered_text, Toast.LENGTH_SHORT).show() }
        }

        binding.nextButton.setOnClickListener {
            if(!quizViewModel.quizStatus){
                quizViewModel.moveToNext()
                updateQuestion()
            }
            else {
                // Challenge 3.2: do end of quiz stuff
                // append correct % to string
                val resultsString = getString(R.string.end_of_quiz) +  "\nYour result: ${quizViewModel.numCorrect} correct out of ${quizViewModel.quizSize}"
                Toast.makeText(this, resultsString, Toast.LENGTH_SHORT).show() // C3.2 temp toast; next steps make it a text view and disable next.
                // this.addContentView(new TextView())
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
        val questionTextResId =  quizViewModel.currentQuestionText
        quizViewModel.answered = false; // reset `answered` state
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val currAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == currAnswer){
            R.string.correct_toast
        }
        else { R.string.incorrect_toast }

        if (userAnswer == currAnswer) quizViewModel.numCorrect++ // C3.2

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        // Challenge 3.1: Preventing Repeat Answers
        // set state to `answered`
        quizViewModel.answered = true
    }
}