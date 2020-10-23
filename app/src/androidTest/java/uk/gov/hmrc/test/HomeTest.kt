package uk.gov.hmrc.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import uk.gov.hmrc.base.BaseActivityTest

@RunWith(JUnit4::class)
class AccessCodesTest : BaseActivityTest() {

    @Test
    fun testSomething() {

        launchScenario()

        homeScreen {
            primaryButton {
                hasText("Primary button")
            }
        }
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("uk.gov.hmrc", appContext.packageName)
    }
}