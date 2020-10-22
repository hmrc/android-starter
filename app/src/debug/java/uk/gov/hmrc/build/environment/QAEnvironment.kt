package uk.gov.hmrc.build.environment

import uk.gov.hmrc.build.AppEnvironment

class QAEnvironment : AppEnvironment() {
    override val displayName = "QA"
    override val baseUrl = "https://jsonplaceholder.typicode.com"
}