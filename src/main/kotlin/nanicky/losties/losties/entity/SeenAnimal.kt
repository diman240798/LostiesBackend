package nanicky.losties.losties.entity

import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.model.UserData
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class SeenAnimal(
        @Id val id: UUID? = null,
        @OneToOne var animal: Animal? = null,
        @OneToOne val userData: UserData? = null,
        @OneToOne val geoAddress: GeoAddress? = null,
        val date: LocalDate? = null,
        var userId: String? = null
)