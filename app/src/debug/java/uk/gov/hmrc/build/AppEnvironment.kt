package uk.gov.hmrc.build

abstract class AppEnvironment {
    abstract val displayName: String
    abstract val baseUrl: String
}