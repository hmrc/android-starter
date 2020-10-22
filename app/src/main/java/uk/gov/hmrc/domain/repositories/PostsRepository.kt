package uk.gov.hmrc.domain.repositories

import uk.gov.hmrc.domain.models.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor() {
    private var posts: List<Post>? = null

    fun save(posts: List<Post>) {
        this.posts = posts
    }

    fun get() : List<Post>? {
        return posts
    }
}