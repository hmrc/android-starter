package uk.gov.hmrc.build

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnvironmentMapper @Inject constructor() {

    fun mapDisplayNameToEnvironment(displayName: String?) = ProductionEnvironment()
}