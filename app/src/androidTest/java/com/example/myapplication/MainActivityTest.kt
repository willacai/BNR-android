package com.example.myapplication

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        Log.d("UITest", "in set up")
        System.out.println("in setup~")
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        Thread.sleep(1000)
        Log.d("UITest", "in Tear Down")
        scenario.close()
    }

    @Test
    fun showFirstQuestionOnLaunch(){
        Log.d("UITest", "in showFirstQuestionOnLaunch")
        onView(withId(R.id.question_text_view))
            .check(matches(withText(R.string.q_australia)))
    }
}