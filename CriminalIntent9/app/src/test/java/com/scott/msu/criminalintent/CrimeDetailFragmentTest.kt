package com.scott.msu.criminalintent

import android.widget.EditText
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario.EmptyFragmentFactory
import com.scott.msu.criminalintent.CrimeDetailFragment
import org.junit.Test
import java.util.UUID
import java.util.regex.Pattern.matches

class CrimeDetailFragmentTest {

    private lateinit var crimeTitleEditText: EditText

    @Test
    fun testCrimeDetailFragment() {
        // Launch the CrimeDetailFragment
        val scenario = launchFragmentInContainer<CrimeDetailFragment>(factory = EmptyFragmentFactory())

        // Interact with the fragment (e.g., simulate user actions)
        onView(withId(R.id.crimeTitle)).perform(typeText("New Crime Title"))

        // Check if the fragment behaves as expected
        onView(withId(R.id.crimeTitle)).check(matches(withText("New Crime Title")))
    }
}


