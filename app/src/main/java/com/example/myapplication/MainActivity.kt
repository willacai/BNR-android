package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import layout.Question

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

    private fun updateQuestion(){
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