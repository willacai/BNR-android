package com.example.myapplication

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import layout.Question

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel(){
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    var answered: Boolean = false
    var numCorrect: Int = 0 // Challenge 3.2: Give a percentage at end of quiz
    private var currentIndex: Int = 0
    private var quizEnd: Boolean = false

    private val questionBank = listOf(
        Question(R.string.q_australia, true),
        Question(R.string.q_oceans, true),
        Question(R.string.q_africa, false),
        Question(R.string.q_mideast, true),
        Question(R.string.q_asia, false)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val quizStatus: Boolean
        get() = quizEnd

    val quizSize: Int
        get() = questionBank.size

    fun moveToNext(){
        if((currentIndex + 1) >= questionBank.size) {
            quizEnd = true
        }
        else {
            currentIndex++ // Challenge 3.2: no wrap
        }
    }

    override fun onCleared(){
        super.onCleared()
        Log.d(TAG, "onCleared() called; ViewModel instance about to be destroyed")
    }
}