package com.example.alpacamusclemaintenance

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.alpacamusclemaintenance.di.AppModule
import com.example.alpacamusclemaintenance.presentation.excercise.PushUpFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Rule
import org.junit.Test

@UninstallModules(AppModule::class)
@HiltAndroidTest
class PushUpFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun givenNoArgs_whenTappedCounterOnce_thenCountOneShouldBeDisplayed() {
        withPushUpRobot {
            tapCounter()
        } verifyThat {
            countIsDisplayedAs("1")
        }
    }
}

fun withPushUpRobot(action: PushUpRobot.() -> Unit): PushUpRobot {
    launchFragmentInHiltContainer<PushUpFragment>()
    return PushUpRobot().apply(action)
}

class PushUpRobot {

    fun tapCounter() {
        onView(withText(R.string.push_up_initial_text))
            .perform(click())
    }

    infix fun verifyThat(verification: PushUpResultRobot.() -> Unit) {
        verification(PushUpResultRobot())
    }
}

class PushUpResultRobot {

    fun countIsDisplayedAs(expectedCount: String) {
        onView(withId(R.id.textView))
            .check(matches(withText(expectedCount)))
    }
}
