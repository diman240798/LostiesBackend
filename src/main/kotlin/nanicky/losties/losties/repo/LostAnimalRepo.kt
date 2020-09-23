package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.LostAnimal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LostAnimalRepo : JpaRepository<LostAnimal, UUID>