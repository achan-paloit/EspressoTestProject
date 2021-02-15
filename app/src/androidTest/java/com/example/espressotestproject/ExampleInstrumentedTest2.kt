package com.example.espressotestproject

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest2 {
    private lateinit var stringToBetyped: String

  /*  @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)*/

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    /*@Test
    fun changeText_sameActivity() {
        // Type text and then press the button.
        *//*onView(withId(R.id.btn_changetext))
            .perform(typeText(stringToBetyped), closeSoftKeyboard())*//*
        onView(withId(R.id.btn_changetext)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.tv_text))
            .check(matches(ViewMatchers.withText(stringToBetyped)))
    }*/

    /*
    A way to know that I have switched to another fragment is by checking the unique view exist
     */
    /*@Test
    fun switchToFragment() {
        onView(withId(R.id.button_first)).perform(click())
        onView(withId(R.id.textview_second)).check(matches(isDisplayed()))
    }

    @Test
    fun switchToFragment2() {
        onView(withId(R.id.button_first)).perform(click())
        onView(withId(R.id.fragmentSecond)).check(matches(isDisplayed()))
    }*/

}