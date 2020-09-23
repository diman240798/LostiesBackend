package nanicky.losties.losties.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*


open class BaseCrudController<M, R: JpaRepository<M, UUID>> {
    @Autowired
    protected lateinit var repo: R

    @PostMapping("/add")
    fun add(@RequestBody model: M) {
        repo.save(model)
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id : UUID) {
        repo.deleteById(id)
    }

    @GetMapping("/get")
    fun getAll(): MutableList<M> {
        return repo.findAll()
    }
}