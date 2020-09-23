package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.entity.TakenAnimal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TakenAnimalRepo : JpaRepository<TakenAnimal, UUID>