package uk.gov.hmrc.domain.commands

import retrofit2.Response
import uk.gov.hmrc.domain.dto.PostDto
import uk.gov.hmrc.domain.models.Post
import uk.gov.hmrc.domain.repositories.PostsRepository
import uk.gov.hmrc.domain.services.PostService
import java.io.IOException
import javax.inject.Inject

class PostsCommand @Inject constructor(
    private val postService: PostService,
    private val postsRepository: PostsRepository
) {

    sealed class Result {
        object Success : Result()
        object ServiceError : Result()
        object NetworkError : Result()
    }

    suspend fun execute(): Result {
        return try {
            val response = postService.posts()
            if (response.isSuccessful) {
                handleSuccessResponse(response)
            } else {
                Result.ServiceError
            }
        } catch (ioException: IOException) {
            Result.NetworkError
        }
    }

    private fun handleSuccessResponse(response: Response<List<PostDto>>): Result.Success {
        val body = response.body()
        body?.let {dto ->
            val posts = dto.map { Post(it.id, it.userId, it.title, it.body) }
            postsRepository.save(posts)
        }
        return Result.Success
    }
}
