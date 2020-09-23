package nanicky.losties.losties.rest

import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.repo.LostAnimalRepo
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/lost")
class LostAnimalController : BaseCrudController<LostAnimal, LostAnimalRepo>() {

}
