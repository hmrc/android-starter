package uk.gov.hmrc.mocks

import dagger.Component
import retrofit2.Retrofit
import uk.gov.hmrc.App
import uk.gov.hmrc.MainActivity
import uk.gov.hmrc.base.BaseActivityTest
import uk.gov.hmrc.common.dagger.AppComponent
import uk.gov.hmrc.common.dagger.AppModule
import uk.gov.hmrc.domain.services.PostService
import uk.gov.hmrc.ui.dashboard.DashboardFragment
import uk.gov.hmrc.ui.home.HomeFragment
import uk.gov.hmrc.ui.notifications.NotificationsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface TestAppComponent : AppComponent {
    fun inject(baseActivityTest: BaseActivityTest)
}