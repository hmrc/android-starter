package uk.gov.hmrc.common.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import uk.gov.hmrc.build.EnvironmentRepository
import uk.gov.hmrc.common.network.HeaderConstants
import uk.gov.hmrc.domain.services.PostService
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Module
open class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    open fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("uk.gov.hmrc.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)

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
            .hostnameVerifier(HostnameVerifier { hostname, session -> true })
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    open fun provideApiBaseUrl(environmentRepository: EnvironmentRepository): String {
        return environmentRepository.getEnvironment().baseUrl
    }

    @Provides
    @Singleton
    @Named("retrofitClient")
    open fun provideRetrofit(
        @Named("baseUrl") baseUrl: String,
        okHttpBuilder: OkHttpClient.Builder
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    open fun providePostService(@Named("retrofitClient") retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }
}