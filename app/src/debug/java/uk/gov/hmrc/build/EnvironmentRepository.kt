package uk.gov.hmrc.build

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnvironmentRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val environmentMapper: EnvironmentMapper
) {

    fun saveEnvironment(environment: AppEnvironment) {
        sharedPreferences.edit(commit = true) { putString(KEY_BUILD_ENVIRONMENT, environment.displayName) }
    }

    fun getEnvironment(): AppEnvironment =
        environmentMapper.mapDisplayNameToEnvironment(sharedPreferences.getString(KEY_BUILD_ENVIRONMENT, ""))

    companion object {
        private const val KEY_BUILD_ENVIRONMENT = "build_environment"
    }
}