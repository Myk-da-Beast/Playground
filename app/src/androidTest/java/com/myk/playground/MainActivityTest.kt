package com.myk.playground

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    // @Test
    // fun typeANumber_resultIsDisplayed() {
    //     onView(withId(R.id.edit_text_factorial)).perform(typeText("1"), closeSoftKeyboard())
    //     onView(withId(R.id.button_compute)).perform(click())
    //
    //     onView(withId(R.id.text_result)).check(matches(isDisplayed()))
    //     onView(withId(R.id.text_result)).check(matches(withText("1")))
    // }
}
