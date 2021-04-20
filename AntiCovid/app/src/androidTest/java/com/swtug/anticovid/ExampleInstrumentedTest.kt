package com.swtug.anticovid


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.swtug.anticovid.Database.DatabaseSchema
import com.swtug.anticovid.Register.Register
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.swtug.anticovid", appContext.packageName)
    }

    @Test
    fun register() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val db = DatabaseSchema(appContext)
        val register = Register(appContext)
        register.register("Wolfgang","Slany",
                "slany.wolfgang@email.com", "Graz","5646513",
                "436556132156","KLhjkjsas")

        assertEquals(true, db.alreadyExistUser("slany.wolfgang@email.com") )
    }

    @Test
    fun registerAlreadyRegistered() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val db = DatabaseSchema(appContext)
        val register = Register(appContext)
        register.register("Wolfgang","Slany",
                "slany.wolfgang@email.com", "Graz","5646513",
                "436556132156","KLhjkjsas")

        assertEquals(false, register.register("Wolfgang","Slany",
                "slany.wolfgang@email.com", "Graz","5646513",
                "436556132156","KLhjkjsas") )
    }


}