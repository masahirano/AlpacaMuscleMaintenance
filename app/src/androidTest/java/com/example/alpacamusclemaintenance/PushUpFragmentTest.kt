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

@RunWith(AndroidJUnit4::class)
class PushUpFragmentTest {

  private val mockDatabase: AppDatabase = mock(AppDatabase::class.java)

  @Test
  fun givenNoArgs_whenTappedCounterOnce_thenCountOneShouldBeDisplayed() {
    val pushUpFragment = PushUpFragment().apply {
      viewModelFactory = createViewModelProviderFactory { PushUpViewModel(mockDatabase) }
    }

    launchFragmentInContainer(
      themeResId = R.style.Theme_MaterialComponents_Light,
      instantiate = { pushUpFragment }
    )
    onView(withText(R.string.push_up_initial_text))
      .perform(click())

    val expectedCount = "1"
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
