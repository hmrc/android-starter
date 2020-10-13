package uk.gov.hmrc.common.dagger

import dagger.Component
import uk.gov.hmrc.MainActivity
import uk.gov.hmrc.ui.dashboard.DashboardFragment
import uk.gov.hmrc.ui.home.HomeFragment
import uk.gov.hmrc.ui.notifications.NotificationsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(dashboardFragment: DashboardFragment)
    fun inject(notificationsFragment: NotificationsFragment)
}