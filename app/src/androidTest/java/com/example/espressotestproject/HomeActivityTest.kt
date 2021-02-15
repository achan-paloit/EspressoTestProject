package com.example.espressotestproject

import android.content.Context
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.*
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    var loginPageActivityRule: ActivityScenarioRule<LoginPageActivity>
            = ActivityScenarioRule(LoginPageActivity::class.java)

    @get:Rule
    var homeActivityRule: ActivityScenarioRule<HomeActivity>
            = ActivityScenarioRule(HomeActivity::class.java)

    @get:Rule
    var registerActivityRule: ActivityScenarioRule<RegisterActivity>
            = ActivityScenarioRule(RegisterActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun cleanup() {
        Intents.release()
        clearSharePref()
    }

    /**
     * Function to add share preference manually to simulated login
     */
    private fun addSharePref() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        context.getSharedPreferences("ESPRESSO_TEST", Context.MODE_PRIVATE).edit().putString("USER_NAME", "alfred").apply()
    }

    fun clearSharePref() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext;
        context.getSharedPreferences("ESPRESSO_TEST", Context.MODE_PRIVATE).edit().remove(
            "USER_NAME").apply()
    }

    /**
     * Test that the app will auto login user has login before
     * Use addSharePref() to simulate that user has login before.
     * Check that HomeActivity is launched
     */
    @Test
    fun checkThatWithPrefItWillLoadHomeActivity() {
        addSharePref()
        launchActivity<LoginPageActivity>()
        intended(hasComponent(HomeActivity::class.java.name)) //Use Intended
        clearSharePref()
    }

    /**
     * Test the bottom navigation bar of the app
     * Test that by tapping on the home tab, home fragment should appear
     */
    @Test
    fun bottomNavTestHome() {
        addSharePref()
        launchActivity<LoginPageActivity>()
        intended(hasComponent(HomeActivity::class.java.name)) //Use Intended
        onView(withId(R.id.navigation_home)).perform(click())
        onView(withId(R.id.cl_fragment_home)).check(matches(isDisplayed()))
        clearSharePref()
    }

    /**
     * Test the bottom navigation bar of the app
     * Test that by tapping on the profile tab, profile fragment should appear
     */
    @Test
    fun bottomNavTestProfile() {
        addSharePref()
        launchActivity<LoginPageActivity>()
        intended(hasComponent(HomeActivity::class.java.name)) //Use Intended
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.cl_fragment_profile)).check(matches(isDisplayed()))
        clearSharePref()
    }

    /**
     * Test the logout function of the app
     * Simulate login, click on the log out button, check that login page appear
     */
    @Test
    fun testLogOut() {
        addSharePref()
        launchActivity<LoginPageActivity>()
        intended(hasComponent(HomeActivity::class.java.name)) //Use Intended
        onView(withId(R.id.navigation_profile)).perform(click())
        onView(withId(R.id.btn_log_out)).perform(click())
        intended(hasComponent(LoginPageActivity::class.java.name)) //Use Intended
        clearSharePref()
    }
}