package nanicky.losties.losties.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class GeoAddress(
        @Id var id: UUID? = null,
        var longtitude: Double? = null,
        var latitude: Double? = null,
        val address: String? = null
)