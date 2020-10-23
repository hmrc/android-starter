package uk.gov.hmrc.test

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
    }
}