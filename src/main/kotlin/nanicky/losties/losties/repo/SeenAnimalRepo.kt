package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.entity.SeenAnimal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeenAnimalRepo : JpaRepository<SeenAnimal, UUID>