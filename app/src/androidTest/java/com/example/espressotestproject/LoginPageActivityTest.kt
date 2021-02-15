package com.example.espressotestproject

import android.content.Context
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginPageActivityTest {

    @get:Rule
    var loginPageActivityRule: ActivityScenarioRule<LoginPageActivity>
            = ActivityScenarioRule(LoginPageActivity::class.java)

    @get:Rule
    var homeActivityRule: ActivityScenarioRule<HomeActivity>
            = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    var registerActivityRule: ActivityScenarioRule<RegisterActivity>
            = ActivityScenarioRule(RegisterActivity::class.java)

    /**
     * Function to do before each test
     * In this case, we are initializing the intent to use "intended" code later
     */
    @Before
    fun setUp() {
        Intents.init()
    }

    /**
     * Function to do after each test
     * Need to clear the intent and clear the share preference to "reset" the state of the each test
     */
    @After
    fun cleanup() {
        Intents.release()
        clearSharePref()
    }

    /**
     * Clear the share preference of the app
     */
    private fun clearSharePref() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        context.getSharedPreferences("ESPRESSO_TEST", Context.MODE_PRIVATE).edit().remove(
                "USER_NAME").apply()
    }

    /**
     * Testing of invalid sign in
     * Type the username and password, click on the sign in button, error text should appear
     */
    @Test
    fun invalidSignIn() {
        onView(withId(R.id.et_username)).perform(clearText(), typeText("123"))
        onView(withId(R.id.et_password)).perform(clearText(), typeText("pwd"))
        onView(withId(R.id.btn_sign_in)).perform(scrollTo()).perform(click())
        onView(withId(R.id.tv_error_text)).check(matches(isDisplayed()))

    }

    /**
     * Testing of valid sign in
     * Type the username and password, click on the sign in button, Homefragment should appear
     */
    @Test
    fun validSignIn() {
        onView(withId(R.id.et_username)).perform(clearText(), typeText("alfred"))
        onView(withId(R.id.et_password)).perform(clearText(), typeText("password"))
        onView(withId(R.id.btn_sign_in)).perform(scrollTo()).perform(click())
        intended(hasComponent(HomeActivity::class.java.name))
    }

    /**
     * Testing of the navigation from Login page to Register page
     * Clicking on the register button should launch register activity
     */
    @Test
    fun validNavWithRegister() {
        onView(withId(R.id.btn_register)).perform(scrollTo()).perform(click())
        intended(hasComponent(RegisterActivity::class.java.name)) //Use Intended
    }

    /**
     * Pressing back from the register page should go back to login page
     */
    @Test
    fun validNavWithLoginPage() {
        onView(withId(R.id.btn_register)).perform(scrollTo()).perform(click())
        pressBack()
        onView(withId(R.id.cl_login_page)).check(matches(isDisplayed()))
    }
}