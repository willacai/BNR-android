package com.example.myapplication

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.*
import org.junit.Test

class QuizViewModelTest {
    @Test
    fun providesExpectedQuestionText() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.q_australia, quizViewModel.currentQuestionText)
    }

    @Test
    fun accurateQuizEnd() {
        val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 4))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.q_asia, quizViewModel.currentQuestionText)
        assertEquals(false, quizViewModel.quizStatus)
        quizViewModel.moveToNext()
        assertEquals(true, quizViewModel.quizStatus)
    }
}