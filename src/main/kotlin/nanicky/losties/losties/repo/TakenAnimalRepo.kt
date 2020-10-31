package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.TakenAnimal
import org.springframework.data.domain.Example
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*

interface TakenAnimalRepo : JpaRepository<TakenAnimal, UUID> {
    fun findByDateBetweenAndAnimal(startDate: LocalDate, endDate: LocalDate, example: Example<TakenAnimal>, pageable: Pageable): List<TakenAnimal>
    fun findByDateBetween(startDate: LocalDate, endDate: LocalDate, pageable: Pageable): List<TakenAnimal>

    fun findByDateAfterAndAnimal(startDate: LocalDate, sample: Example<TakenAnimal>, pageable: Pageable): List<TakenAnimal>
    fun findByDateAfter(startDate: LocalDate, pageable: Pageable): List<TakenAnimal>

    fun findByDateBeforeAndAnimal(endDate: LocalDate, sample: Example<TakenAnimal>, pageable: Pageable): List<TakenAnimal>
    fun findByDateBefore(endDate: LocalDate, pageable: Pageable): List<TakenAnimal>
}