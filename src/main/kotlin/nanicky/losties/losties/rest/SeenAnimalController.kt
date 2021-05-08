package nanicky.losties.losties.rest

import nanicky.losties.losties.AddAnimalRequest
import nanicky.losties.losties.entity.SeenAnimal
import nanicky.losties.losties.enums.PublicationTypes
import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.model.AnimalPublication
import nanicky.losties.losties.repo.*
import nanicky.losties.losties.util.AnimalType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*


@RestController
@RequestMapping("/seen")
class SeenAnimalController : BaseCrudController<SeenAnimal, SeenAnimalRepo>() {


    @Autowired
    lateinit var photoManager: PhotoManager
    @Autowired
    lateinit var animalRepo: AnimalRepo
    @Autowired
    lateinit var geoAddressRepo: GeoAddressRepo
    @Autowired
    lateinit var userRepo: UserDataRepo

    @PostMapping("/add")
    fun add(@RequestBody request: AddAnimalRequest) {

        val photoIds = mutableListOf<UUID>()

        request.photos.forEach {
            val file = photoManager.savePhoto(it, PublicationTypes.SEEN)
            photoIds.add(UUID.fromString(file!!.name.toString().substringBefore(".")))
        }

        val animal = request.animal!!
        animal.photoIds = photoIds
        animalRepo.save(animal)

        val user = request.userData!!
        userRepo.save(user)

        val geoAddress = request.geoAddress!!
        geoAddressRepo.save(geoAddress)

        val seenAnimal = SeenAnimal(
                UUID.randomUUID(),
                animal,
                user,
                geoAddress,
                date = LocalDate.now(),
                userId = request.userId
        )

        repo.save(seenAnimal)

    }

    @GetMapping("/getAll")
    fun getAll() : List<AnimalPublication> =
            repo.findAll().map { AnimalPublication(it) }

    @GetMapping("/getAllUser")
    fun getAllUser(@RequestParam("userId") userId: String): List<AnimalPublication> =
            repo.findAll(Example.of(SeenAnimal(userId = userId))).map { AnimalPublication(it) }

    val ITEMS_ON_PAGE = 12

    @GetMapping("/get")
    fun getList(
            @RequestParam("dateStart") dateStartStr: String?,
            @RequestParam("dateEnd") dateEndStr: String?,
            @RequestParam("breed") breed: String?,
            @RequestParam("type") type: AnimalType?,
            @RequestParam("page") page: Int?
    ): List<AnimalPublication> {

        val page = page ?: 0

        var animal: Animal? = null

        breed?.let {
            animal = animal ?: Animal()
            animal!!.breed = it
        }
        type?.let {
            animal = animal ?: Animal()
            animal!!.type
        }


        val example = if (animal != null) Example.of(SeenAnimal(animal = animal)) else null


        val pageable = PageRequest.of(page, ITEMS_ON_PAGE, Sort.Direction.DESC, "date")

        val result: List<SeenAnimal>
        if (dateStartStr != null && dateEndStr != null) {
            if (example != null) {
                result = repo.findByDateBetweenAndAnimal(LocalDate.parse(dateStartStr), LocalDate.parse(dateEndStr), example, pageable)
            } else {
                result = repo.findByDateBetween(LocalDate.parse(dateStartStr), LocalDate.parse(dateEndStr), pageable)
            }
        } else if (dateStartStr != null) {
            if (example != null) {
                result = repo.findByDateAfterAndAnimal(LocalDate.parse(dateStartStr), example, pageable)
            } else {
                result = repo.findByDateAfter(LocalDate.parse(dateStartStr), pageable)
            }
        } else if (dateEndStr != null) {
            if (example != null) {
                result = repo.findByDateBeforeAndAnimal(LocalDate.parse(dateEndStr), example, pageable)
            } else {
                result = repo.findByDateBefore(LocalDate.parse(dateEndStr), pageable)
            }
        } else {
            if (example != null) {
                result = repo.findAll(example, pageable).content
            } else {
                result = repo.findAll(pageable).content
            }
        }

        return result.map { AnimalPublication(it) }
    }
}
