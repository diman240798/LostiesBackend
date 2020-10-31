package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.LostAnimal
import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface LostAnimalRepo : JpaRepository<LostAnimal, UUID> {

    fun findByDateBetweenAndAnimal(dateStart: LocalDate, dateEnd: LocalDate, example: Example<LostAnimal>, page: Pageable): List<LostAnimal>
    fun findByDateBetween(dateStart: LocalDate, dateEnd: LocalDate, page: Pageable): List<LostAnimal>

    fun findByDateAfterAndAnimal(dateStart: LocalDate, example: Example<LostAnimal>, pageable: Pageable): List<LostAnimal>
    fun findByDateAfter(dateStart: LocalDate, pageable: Pageable): List<LostAnimal>

    fun findByDateBeforeAndAnimal(dateEnd: LocalDate, example: Example<LostAnimal>, page: Pageable): List<LostAnimal>
    fun findByDateBefore(dateEnd: LocalDate, page: Pageable): List<LostAnimal>

}