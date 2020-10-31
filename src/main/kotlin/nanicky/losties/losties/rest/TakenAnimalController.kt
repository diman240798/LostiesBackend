package nanicky.losties.losties.rest

import nanicky.losties.losties.entity.TakenAnimal
import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.repo.TakenAnimalRepo
import nanicky.losties.losties.util.AnimalType
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate


@RestController
@RequestMapping("/taken")
class TakenAnimalController : BaseCrudController<TakenAnimal, TakenAnimalRepo>() {

    val ITEMS_ON_PAGE = 12

    @GetMapping("/get")
    fun getList(
            @RequestParam("dateStart") dateStartStr: String?,
            @RequestParam("dateEnd") dateEndStr: String?,
            @RequestParam("breed") breed: String?,
            @RequestParam("type") type: AnimalType?,
            @RequestParam("page") page: Int?
    ) : List<TakenAnimal> {

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


        val example = if (animal != null) Example.of(TakenAnimal(animal = animal)) else null


        val pageable = PageRequest.of(page, ITEMS_ON_PAGE, Sort.Direction.DESC, "date")

        val result : List<TakenAnimal>
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

        return result
    }
}
