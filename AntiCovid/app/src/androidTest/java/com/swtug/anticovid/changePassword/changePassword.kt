package com.swtug.anticovid.changePassword

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.swtug.anticovid.R
import com.swtug.anticovid.TestUtils
import com.swtug.anticovid.models.User
import com.swtug.anticovid.repositories.PreferencesRepo
import com.swtug.anticovid.view.profile.ProfileFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class changePasswordtest {
    private lateinit var navController: TestNavHostController
    private val TEST_USER_OLD_PWD: String = "test@test.com"
    private val TEST_USER_NEW_PWD: String = "testtest"


    @Before
    fun setup() {
        PreferencesRepo.saveUser(
            InstrumentationRegistry.getInstrumentation().targetContext,
            testUser
        )


        navController = Mockito.mock(NavController::class.java)

        val mainScenario =
            launchFragmentInContainer<ProfileFragment>(
                themeResId = R.style.Theme_AntiCovid,

                )

        mainScenario.withFragment {
            Navigation.setViewNavController(requireView(), navController)
        }
    }


    private fun checkCorrectUserShown(user: User) {
        onView(ViewMatchers.withId(R.id.editTextProfileName))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.name)))

        onView(ViewMatchers.withId(R.id.editTextProfileSurname))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.surname)))

        onView(ViewMatchers.withId(R.id.editTextProfileEmail))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.email)))

        onView(ViewMatchers.withId(R.id.editTextProfileAddress))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.address)))

        onView(ViewMatchers.withId(R.id.editTextProfilePhoneNumber))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.phonenumber)))

        onView(ViewMatchers.withId(R.id.editTextProfileSocialSecurityID))
            .check(ViewAssertions.matches(ViewMatchers.withText(user.secid)))
    }

    @Test
    fun testCurrentUserCorrectLoaded() {
        checkCorrectUserShown(testUser)
    }


    @After
    fun tearDown() {
        TestUtils.clearSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext)
    }


}
