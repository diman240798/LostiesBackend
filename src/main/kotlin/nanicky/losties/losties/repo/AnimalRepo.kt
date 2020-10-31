package nanicky.losties.losties.repo

import nanicky.losties.losties.model.Animal
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AnimalRepo : JpaRepository<Animal, UUID>