package nanicky.losties.losties.rest

import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.repo.LostAnimalRepo
import nanicky.losties.losties.repo.SeenAnimalRepo
import nanicky.losties.losties.repo.TakenAnimalRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var lostAnimalRepo: LostAnimalRepo
    @Autowired
    private lateinit var seenAnimalRepo: SeenAnimalRepo
    @Autowired
    private lateinit var takenAnimalRepo: TakenAnimalRepo

}