package uk.gov.hmrc.build.environment

import uk.gov.hmrc.build.AppEnvironment

class DevelopmentEnvironment : AppEnvironment() {
    override val displayName = "Development"
    override val baseUrl = "https://jsonplaceholder.typicode.com"
}