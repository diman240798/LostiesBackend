package nanicky.losties.losties.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Address(
        @Id var id: UUID? = null,
        var city: String? = null,
        var street: String? = null,
        var houseNumber: String? = null
)