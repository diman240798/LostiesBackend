package nanicky.losties.losties.model

import nanicky.losties.losties.util.AnimalType
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Animal(@Id val id: UUID ?= null, var name: String?= null, var type: AnimalType ?= null, var breed: String?= null, @ElementCollection var photoIds: List<UUID>?= null)