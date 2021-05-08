package nanicky.losties.losties.rest

import nanicky.losties.losties.model.AnimalPublication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import java.util.*


open class BaseCrudController<M, R : JpaRepository<M, UUID>> {
    @Autowired
    protected lateinit var repo: R

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: UUID) {
        repo.deleteById(id)
    }
}