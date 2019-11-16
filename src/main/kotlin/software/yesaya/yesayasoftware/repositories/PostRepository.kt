package software.yesaya.yesayasoftware.repositories

import org.springframework.data.jpa.repository.JpaRepository
import software.yesaya.yesayasoftware.entities.Post

interface PostRepository : JpaRepository<Post, Long>