package nanicky.losties.losties.rest

import com.github.kotlintelegrambot.bot
import nanicky.losties.losties.repo.LostAnimalRepo
import nanicky.losties.losties.repo.PhotoManager
import nanicky.losties.losties.repo.PhotoType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.util.*

@RestController
@RequestMapping("/bot")
class SocialNetworksBotController {

    @Autowired
    private lateinit var lostAnimalRepo: LostAnimalRepo
    @Autowired
    private lateinit var photoManager: PhotoManager

    private val bot = bot {
        token = "1431923394:AAHxxUVeeTtotzm1FfsfqlLF2jlj0-vqC60"
//        token = "1931110:d1e6efad70140279c9ce60507f506c2b"
    }

    private val BOT_CHAT_ID_STRING = "@losties_bot"
    private val BOT_CHAT_ID_LONG = 1431923394L

    private val CHAT_ID_STRING = "@lostiespets"
    private val CHAT_ID_LONG = -1001232080105L

    private val LOST_ANIMAL_PATTERN = "Потярялось животное типа: %s по адресу: %s. Кличка: %s"

    @GetMapping("/send-all-lost")
    fun sendLostToAll(@RequestParam id: UUID) {

        val option = lostAnimalRepo.findById(id)
        if (!option.isPresent) {
            throw PublicationNotFoundException()
        }

        val lostAnimal = option.get()
        val address = lostAnimal.geoAddress!!.address

        val animal = lostAnimal.animal!!
        val photoIds = animal.photoIds
        val type = animal.type!!.rusName
        val name = animal.name

        val photosCount = photoIds!!.size

        val caption = String.format(LOST_ANIMAL_PATTERN, type, address, name)

        sendTelegram(photosCount, photoIds, caption)


    }

    private fun sendTelegram(photosCount: Int, photoIds: List<UUID>, caption: String) {
        if (photosCount > 1) {
            for (i in 0 until photosCount) {
                val photoFile = photoManager.getPhotoFile(photoIds[0], PhotoType.LOST)!!
                if (i < photosCount - 1) {
                    bot.sendPhoto(CHAT_ID_LONG, photoFile)
                } else {
                    bot.sendPhoto(CHAT_ID_LONG, photoFile, caption)
                }
            }
        } else {
            val photoFile = photoManager.getPhotoFile(photoIds[0], PhotoType.LOST)!!
            bot.sendPhoto(CHAT_ID_LONG, photoFile, caption)
        }
    }
}

/*  private fun sendLostToTelegramTest() {
        bot.sendPhoto(CHAT_ID_LONG, File("E:\\CAT\\1.jpg"))
    }
*/