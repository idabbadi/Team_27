package com.swtug.anticovid


import com.swtug.anticovid.Register.Register
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun registerValidityCheckWrong()
    {
        val valid = Register.stringValidityCheck("aaa", "bbb", "aaa", "", "", "", "")
        assertEquals(false, valid)
    }

    @Test
    fun registerValidityCheckWrongPassword()
    {
        val valid = Register.stringValidityCheck("aaa", "bbb", "aaa", "aaa", "aaa", "aaa", "asd")
        assertEquals(false, valid)
    }

    @Test
    fun registerValidityCheckTrue()
    {
        val valid = Register.stringValidityCheck("aaa", "bbb", "aaa", "aaa", "aaa", "aaa", "asdasdasd")
        assertEquals(true, valid)
    }
}