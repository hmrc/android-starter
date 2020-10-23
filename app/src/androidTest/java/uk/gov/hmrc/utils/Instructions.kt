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
package uk.gov.hmrc.utils

import android.view.Gravity
import android.view.View
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.test.rule.ActivityTestRule
import com.azimolabs.conditionwatcher.Instruction
import kotlin.reflect.KClass

class WaitForNavDrawToClose(private val drawer: DrawerLayout) : Instruction() {

    override fun checkCondition() = !drawer.isDrawerVisible(Gravity.START)

    override fun getDescription() = "Nav drawer didn't close"
}

class WaitForFragment(private val fragmentManager: FragmentManager, private val fragmentClass: KClass<*>) : Instruction() {

    override fun checkCondition() = fragmentManager.fragments.any { it::class == fragmentClass }

    override fun getDescription() = "Fragment was never shown"
}

class WaitForViewToBeVisible(private val activityRule: ActivityTestRule<*>, private val viewId: Int) : Instruction() {

    override fun getDescription() = "View was never visible"

    override fun checkCondition() = activityRule.activity.findViewById<View>(viewId)?.isVisible ?: false
}
