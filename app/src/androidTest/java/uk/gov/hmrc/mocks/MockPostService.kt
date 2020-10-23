package uk.gov.hmrc.mocks

import retrofit2.Response
import uk.gov.hmrc.base.loadJson
import uk.gov.hmrc.domain.dto.PostDto
import uk.gov.hmrc.domain.services.PostService

class MockPostService : PostService {
    var posts: Response<List<PostDto>> = Response.success("data/posts.json".loadJson())
    var post: Response<PostDto> = Response.success("data/post.json".loadJson())

    override suspend fun posts(): Response<List<PostDto>> {
        return posts
    }

    override suspend fun post(id: String): Response<PostDto> {
        return post
    }
}