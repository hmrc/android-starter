package uk.gov.hmrc.domain.models

data class Post(
    var id: Int,
    var userId: Int,
    var title: String? = null,
    var body: String? = null
)
