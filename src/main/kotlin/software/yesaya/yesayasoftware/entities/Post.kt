package software.yesaya.yesayasoftware.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Post {

    @Id
    @GeneratedValue
    var id: Long? = null

    @NotBlank(message = "Please enter title")
    @Size(min = 2, message = "Please check title correctly, minimum character is 2.")
    var title: String? = null

    var content: String? = null

    @NotBlank(message = "Please enter publish date")
    @Size(min = 10, max = 10, message = "Please confirm publish date format(YYYY-mm-dd).")
    var publish_date: String? = null

    override fun toString(): String {
        return super.toString()
    }
}