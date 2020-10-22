package uk.gov.hmrc.domain.dto

data class PostDto(
    var id: Int,
    var userId: Int,
    var title: String? = null,
    var body: String? = null
)
