package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import layout.Question

private const val TAG = "mainActivity" // why do we add it before the main class def?
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentIndex: Int = 0

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

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size // wraparound
            updateQuestion()
        }

        updateQuestion()

    }
    override fun onStart(){
        super.onStart() // calling superclass implementation; i.e. "original, AND..."
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
        Log.d(TAG, "Current q index: $currentIndex")
        val questionTextResId =  questionBank[currentIndex].textResId // get reference
        binding.questionTextView.setText(questionTextResId) // use reference
    }

    private fun checkAnswer(userAnswer: Boolean){
        val currAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == currAnswer){
            R.string.correct_toast
        }
        else { R.string.incorrect_toast }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}