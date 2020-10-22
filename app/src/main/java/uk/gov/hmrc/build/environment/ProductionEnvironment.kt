package uk.gov.hmrc.build.environment

import uk.gov.hmrc.build.AppEnvironment

class ProductionEnvironment : AppEnvironment() {
    override val displayName = "Production"
    override val baseUrl = "https://jsonplaceholder.typicode.com"
}