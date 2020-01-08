package com.example.alpacamusclemaintenance

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.ui.PushUpFragment
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

private val mockDatabase: AppDatabase = mock(AppDatabase::class.java)

@RunWith(AndroidJUnit4::class)
class PushUpFragmentTest {

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
  launchFragmentInContainer(
    themeResId = R.style.Theme_MaterialComponents_Light,
    instantiate = {
      PushUpFragment().apply {
        viewModelFactory = createViewModelProviderFactory { PushUpViewModel(mockDatabase) }
      }
    }
  )

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

fun createViewModelProviderFactory(
  viewModelCreator: () -> ViewModel
): ViewModelProvider.Factory =
  object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
      viewModelCreator() as T
  }
