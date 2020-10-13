package uk.gov.hmrc.common.dagger

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uk.gov.hmrc.common.network.HeaderConstants
import javax.inject.Named
import javax.inject.Singleton

@Module
open class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    open fun provideOKHttpClient(): OkHttpClient.Builder {
        val timberLogger = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
            redactHeader(HeaderConstants.Authorization.NAME)
        }
        return OkHttpClient.Builder()
            .addInterceptor(timberLogger)
    }

    @Provides
    @Singleton
    @Named("api")
    fun provideApiRetrofit(
        @Named("apiBaseUrl") baseUrl: String,
        okHttpBuilder: OkHttpClient.Builder
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }
}