package nanicky.losties.losties.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Address(@Id val id: UUID, var city: String, var street: String, var houseNumber: String)