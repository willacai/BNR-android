package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
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
            // trigger a toast
            Toast.makeText(
                this, // gets add'l info about where the toast is showing
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.falseButton.setOnClickListener {
            Toast.makeText(
                this, // gets add'l info about where the toast is showing
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()
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
}