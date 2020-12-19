package nanicky.losties.losties.repo

import org.springframework.stereotype.Component
import java.io.File
import java.util.*

enum class PhotoType {
    LOST, SEEN, TAKEN, SELLING, WALK_YOUR_ANIMAL
}

@Component
class PhotoManager {

    val path: String = "./photos"

    fun getPhotoFile(id: UUID, photoType: PhotoType): File? {
        val photoDir = File(path, photoType.name)
        if (!photoDir.exists()) {
//            LOG.info("Photo dir ${photoDir.name} was not created yet!")
            return null
        }
        val photoFile = photoDir.listFiles().firstOrNull { it.name.contains(id.toString()) }
        return photoFile
    }


    fun savePhoto(data: ByteArray, photoType: PhotoType, id: UUID = UUID.randomUUID()): File? {
        try {
            val photoDir = File(path, photoType.name).absoluteFile
            if (!photoDir.exists() and !photoDir.mkdirs()) {
                throw RuntimeException("Cant create photo dir: ${photoDir.absolutePath}")
            }
            val photoFile = File(photoDir, "$id.png")
            photoFile.createNewFile()
            photoFile.writeBytes(data)
            return photoFile
        } catch (ex: Exception) {
//            LOG.error("Error saving photo: $id", ex)
            return null
        }

    }
}
