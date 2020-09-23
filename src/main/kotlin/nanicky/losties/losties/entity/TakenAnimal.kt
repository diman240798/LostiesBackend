package nanicky.losties.losties.entity

import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.model.UserData
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class TakenAnimal(
        @Id val id: UUID,
        @OneToOne var animal: Animal,
        @OneToOne val user: UserData,
        @OneToOne val geoAddress: GeoAddress
)