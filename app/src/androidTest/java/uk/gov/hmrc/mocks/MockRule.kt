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
package uk.gov.hmrc.mocks

import androidx.test.platform.app.InstrumentationRegistry
import it.cosenonjaviste.daggermock.DaggerMockRule
import uk.gov.hmrc.App
import uk.gov.hmrc.common.dagger.AppComponent
import uk.gov.hmrc.common.dagger.AppModule
import uk.gov.hmrc.domain.services.PostService

class MockRule : DaggerMockRule<AppComponent>(AppComponent::class.java, AppModule(app)) {
    init {
        set { component -> app.appComponent = component }

        provides(PostService::class.java, MockPostService())
    }

    companion object {

        private val app: App
            get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App
    }
}
