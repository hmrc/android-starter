/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.hmrc.base

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Rule
import timber.log.Timber
import uk.gov.hmrc.MainActivity
import uk.gov.hmrc.mocks.MockPostService
import uk.gov.hmrc.mocks.MockRule
import uk.gov.hmrc.screens.TestHomeScreen
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.util.*

open class BaseActivityTest() {

//    @Rule
//    @JvmField
//    val mockRule = MockRule()

    private lateinit var activityScenario: ActivityScenario<out Activity>

    open val autoLaunchActivity = true

    val homeScreen: TestHomeScreen by lazy {
        TestHomeScreen()
    }

//    val postService = MockPostService()

    @Before
    fun start() {
        if (autoLaunchActivity) {
            launchScenario()
        }
//        mockRule.provides(MockPostService::class.java, postService)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    @After
    fun tearDown() {
    }

    fun launchScenario(intent: Intent? = null) {
        intent?.let {
            activityScenario = launch<MainActivity>(intent)
        } ?: run {
            activityScenario = launch(MainActivity::class.java)
        }
    }
}

fun String.loadString(): String {
    val returnString = StringBuilder()
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(
            InputStreamReader(
                InstrumentationRegistry.getInstrumentation().context.assets.open(this),
                "UTF-8"
            )
        )
        // do reading, usually loop until end of file reading
        var line: String?
        line = reader.readLine()
        while (line != null) {
            // process line
            returnString.append(line)
            line = reader.readLine()
        }
    } catch (e: IOException) {
        Timber.e(e)
    } finally {
        if (reader != null) {
            try {
                reader.close()
            } catch (e: IOException) {
                Timber.e(e)
            }
        }
    }
    return returnString.toString()
}

inline fun <reified T> emptyResponse(): T = Gson().fromJson("", T::class.java)

inline fun <reified T> String.loadJson(type: Type = T::class.java): T =
    Gson().fromJson(loadString(), type)
