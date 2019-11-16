package software.yesaya.yesayasoftware.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import software.yesaya.yesayasoftware.entities.Post
import software.yesaya.yesayasoftware.repositories.PostRepository
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = arrayOf("http://localhost:4200"), allowedHeaders = arrayOf("*"))
class PostController {

    @Autowired
    private val postRepository: PostRepository? = null

    internal lateinit var errors: MutableMap<String, String?>

    val posts: List<Post>
        get() = postRepository!!.findAll()


    @GetMapping("/posts")
    fun getAllPost(): Any {
        val response = mutableMapOf<String, List<Post>>()

        response["posts"] = posts

        return response
    }

    @GetMapping("/posts/{id}")
    fun getPost(@PathVariable id: Long?): Optional<Post> {
        return postRepository!!.findById(id!!)
    }

    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: Long?): Boolean {
        postRepository!!.deleteById(id!!)

        return true
    }

    @PutMapping("/posts/{id}")
    fun updatePost(@RequestBody post: Post): Post {
        return postRepository!!.save(post)
    }

    /**
     * Save the new customer in the database
     *
     * @param customer
     * @param bindingResult
     * @return Object i.e FieldError | Customer
     */
    @PostMapping("/posts")
    fun createPost(@RequestBody @Valid post: Post, bindingResult: BindingResult): ResponseEntity<Any> {
        if (bindingResult.hasErrors()) {
            //There is errors in the nindingResult, we need to loop through
            errors = HashMap()

            for (error in bindingResult.fieldErrors) {
                errors[error.field] = error.defaultMessage
            }

            return ResponseEntity(errors, HttpStatus.NOT_ACCEPTABLE)
        }

        //Successfully pass the validation check, ready to save.
        return ResponseEntity(postRepository!!.save(post), HttpStatus.ACCEPTED)
    }

}