package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var timerToggle: ToggleButton
    private lateinit var countdownDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // inflates activity_main view, instantiates vars defined there

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        timerToggle = findViewById(R.id.timer_toggle)
        countdownDisplay = findViewById(R.id.countdown_display)

        // init countdown with default timer
        countdownDisplay.setText("Hello")

        trueButton.setOnClickListener { view: View ->
            // trigger a toast
            Toast.makeText(
                this, // gets add'l info about where the toast is showing
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            ).show()
        }

        falseButton.setOnClickListener {
            Toast.makeText(
                this, // gets add'l info about where the toast is showing
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ).show()
        }

        // timer toggle logic
        timerToggle.setOnClickListener{
            // toggle timer pause play
            // display toast
        }

    }
}