package com.example.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import java.lang.String
import kotlin.Long

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var timerToggle: ToggleButton
    private lateinit var countdownDisplay: TextView
    private lateinit var timerObject: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // inflates activity_main view, instantiates vars defined there

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        timerToggle = findViewById(R.id.timer_toggle)
        countdownDisplay = findViewById(R.id.countdown_display)
        timerObject = object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                countdownDisplay.setText(String.valueOf(millisUntilFinished/1000))
            }

            override fun onFinish() {
                countdownDisplay.setText("Time's up!")
            }
        }

        // init countdown with default timer
        countdownDisplay.setText("Timer")

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
        timerToggle.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                timerObject.start()
                Toast.makeText(this, R.string.start_timer_toast, Toast.LENGTH_SHORT).show()
            }
            else {
                timerObject.cancel()
                countdownDisplay.append(" - Paused")
                Toast.makeText(this, R.string.stop_timer_toast, Toast.LENGTH_SHORT).show()
            }

        }


    }
}
