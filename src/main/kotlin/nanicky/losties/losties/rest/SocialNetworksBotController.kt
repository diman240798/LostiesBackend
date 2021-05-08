package nanicky.losties.losties.rest

import com.github.kotlintelegrambot.bot
import nanicky.losties.losties.enums.PublicationTypes
import nanicky.losties.losties.model.AnimalPublicationTyped
import nanicky.losties.losties.repo.LostAnimalRepo
import nanicky.losties.losties.repo.PhotoManager
import nanicky.losties.losties.repo.SeenAnimalRepo
import nanicky.losties.losties.repo.TakenAnimalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/bot")
class SocialNetworksBotController {

    @Autowired
    private lateinit var lostAnimalRepo: LostAnimalRepo
    @Autowired
    private lateinit var seenAnimalRepo: SeenAnimalRepo
    @Autowired
    private lateinit var takenAnimalRepo: TakenAnimalRepo
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

    @PostMapping("/send-all")
    fun sendPublicaiton(@RequestParam publicationId: UUID, @RequestParam publicationTypes: PublicationTypes) {

        var publicationOption: Optional<AnimalPublicationTyped> = getPublication(publicationTypes, publicationId)

        if (!publicationOption.isPresent) {
            throw PublicationNotFoundException()
        }

        val publicationTyped = publicationOption.get()
        val address = publicationTyped.geoAddress!!.address

        val animal = publicationTyped.animal!!
        val photoIds = animal.photoIds!!
        val type = animal.type!!.rusName
        val name = animal.name

        val caption = String.format(LOST_ANIMAL_PATTERN, type, address, name)

        sendTelegram(photoIds, caption)
    }

    private fun getPublication(publicationTypes: PublicationTypes, publicationId: UUID): Optional<AnimalPublicationTyped> {
        var publicationOption: Optional<AnimalPublicationTyped> = Optional.empty()
        if (publicationTypes == PublicationTypes.LOST) {
            val option = lostAnimalRepo.findById(publicationId)
            if (!option.isPresent) {
                throw PublicationNotFoundException()
            }
            publicationOption = Optional.of(AnimalPublicationTyped(option.get()))
        } else if (publicationTypes == PublicationTypes.SEEN) {
            val option = seenAnimalRepo.findById(publicationId)
            if (!option.isPresent) {
                throw PublicationNotFoundException()
            }
            publicationOption = Optional.of(AnimalPublicationTyped(option.get()))
        } else if (publicationTypes == PublicationTypes.TAKEN) {
            val option = takenAnimalRepo.findById(publicationId)
            if (!option.isPresent) {
                throw PublicationNotFoundException()
            }
            publicationOption = Optional.of(AnimalPublicationTyped(option.get()))
        }
        return publicationOption
    }

    private fun sendTelegram(photoIds: List<UUID>, caption: String) {
        val photosCount = photoIds.size
        if (photosCount > 1) {
            for (i in 0 until photosCount) {
                val photoFile = photoManager.getPhotoFile(photoIds[0], PublicationTypes.LOST)!!
                if (i < photosCount - 1) {
                    bot.sendPhoto(CHAT_ID_LONG, photoFile)
                } else {
                    bot.sendPhoto(CHAT_ID_LONG, photoFile, caption)
                }
            }
        } else {
            val photoFile = photoManager.getPhotoFile(photoIds[0], PublicationTypes.LOST)!!
            bot.sendPhoto(CHAT_ID_LONG, photoFile, caption)
        }
    }
}

/*  private fun sendLostToTelegramTest() {
        bot.sendPhoto(CHAT_ID_LONG, File("E:\\CAT\\1.jpg"))
    }
*/