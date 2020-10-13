package uk.gov.hmrc

import android.app.Application
import android.content.Context
import uk.gov.hmrc.common.dagger.AppComponent
import uk.gov.hmrc.common.dagger.AppModule
import uk.gov.hmrc.common.dagger.DaggerAppComponent

open class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    open fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        fun get(context: Context) = context.applicationContext as App
    }
}