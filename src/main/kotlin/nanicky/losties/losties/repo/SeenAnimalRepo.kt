package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.SeenAnimal
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface SeenAnimalRepo : JpaRepository<SeenAnimal, UUID> {

    fun findByDateBetweenAndAnimal(startDate: LocalDate, endDate: LocalDate, example: Example<SeenAnimal>, pageable: PageRequest): List<SeenAnimal>
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate, pageable: Pageable): List<SeenAnimal>

    fun findByDateAfterAndAnimal(startDate: LocalDate, sample: Example<SeenAnimal>, pageable: PageRequest): List<SeenAnimal>
    fun findByDateAfter(startDate: LocalDate, pageable: Pageable): List<SeenAnimal>

    fun findByDateBeforeAndAnimal(endDate: LocalDate, sample: Example<SeenAnimal>, pageable: PageRequest): List<SeenAnimal>
    fun findByDateBefore(endDate: LocalDate, pageable: Pageable): List<SeenAnimal>

}