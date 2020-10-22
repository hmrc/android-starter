package uk.gov.hmrc.build

import uk.gov.hmrc.build.environment.DevelopmentEnvironment
import uk.gov.hmrc.build.environment.ProductionEnvironment
import uk.gov.hmrc.build.environment.QAEnvironment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnvironmentMapper @Inject constructor() {

    companion object {
        val environments = listOf(
            DevelopmentEnvironment(),
            QAEnvironment(),
            ProductionEnvironment()
        )
    }

    private val defaultEnvironment = DevelopmentEnvironment()

    fun mapDisplayNameToEnvironment(displayName: String?) = environments.firstOrNull {
        it.displayName == displayName
    } ?: defaultEnvironment
}