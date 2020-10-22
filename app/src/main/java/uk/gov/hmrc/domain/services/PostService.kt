package uk.gov.hmrc.domain.services

import retrofit2.Response
import retrofit2.http.GET
import uk.gov.hmrc.domain.dto.PostDto

interface PostService {

    @GET("/posts")
    suspend fun posts(): Response<List<PostDto>>

    @GET("/posts/{id}")
    suspend fun post(id: String): Response<PostDto>
}