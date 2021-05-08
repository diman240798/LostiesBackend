package nanicky.losties.losties.rest

import nanicky.losties.losties.enums.PublicationTypes
import nanicky.losties.losties.repo.PhotoManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/photo")
class PhotoController {

    @Autowired
    lateinit var photoManager: PhotoManager

    @GetMapping("/get")
    fun getPhoto(@RequestParam("id") id: UUID, @RequestParam("type") type: PublicationTypes): ByteArray {
        val photoFile = photoManager.getPhotoFile(id, type)
        return photoFile?.readBytes() ?: ByteArray(0)
    }
}
