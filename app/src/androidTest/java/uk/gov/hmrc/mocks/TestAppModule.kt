package uk.gov.hmrc.mocks

import android.app.Application
import retrofit2.Retrofit
import uk.gov.hmrc.common.dagger.AppModule
import uk.gov.hmrc.domain.services.PostService

open class TestAppModule(
    private val app: Application,
    private val postService: PostService
) : AppModule(app) {

    override fun providePostService(retrofit: Retrofit): PostService = postService

}