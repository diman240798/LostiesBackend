package nanicky.losties.losties.model

import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Animal(@Id val id: UUID, var name: String, var breed: String, @ElementCollection var photoIds: List<UUID>)