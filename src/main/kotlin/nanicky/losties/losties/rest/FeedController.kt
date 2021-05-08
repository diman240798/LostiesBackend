package nanicky.losties.losties.rest

import nanicky.losties.losties.model.AnimalPublicationTyped
import nanicky.losties.losties.repo.LostAnimalRepo
import nanicky.losties.losties.repo.SeenAnimalRepo
import nanicky.losties.losties.repo.TakenAnimalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/feed")
class FeedController {

    @Autowired
    private lateinit var seenAnimalRepo: SeenAnimalRepo
    @Autowired
    private lateinit var takenAnimalRepo: TakenAnimalRepo
    @Autowired
    private lateinit var lostAnimalRepo: LostAnimalRepo


    @GetMapping("/get")
    fun getList() : List<AnimalPublicationTyped> {

        val startDate = LocalDate.now().minusDays(5)

        val pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "date")

        val result = mutableListOf<AnimalPublicationTyped>()

        result.addAll(lostAnimalRepo.findByDateAfter(startDate, pageable).map { AnimalPublicationTyped(it) })
        result.addAll(seenAnimalRepo.findByDateAfter(startDate, pageable).map { AnimalPublicationTyped(it) })
        result.addAll(takenAnimalRepo.findByDateAfter(startDate, pageable).map { AnimalPublicationTyped(it) })

        return result
    }
}
