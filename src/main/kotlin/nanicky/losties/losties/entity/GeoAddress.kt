package nanicky.losties.losties.entity

import nanicky.losties.losties.model.Address
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class GeoAddress(
        @Id val id: UUID,
        var longtitude: Double,
        var latitude: Double,
        @OneToOne val address: Address
)