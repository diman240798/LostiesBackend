package nanicky.losties.losties.model

import nanicky.losties.losties.entity.GeoAddress
import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.entity.SeenAnimal
import nanicky.losties.losties.entity.TakenAnimal
import java.util.*

open class AnimalPublication(
        val id: UUID? = null,
        var animal: Animal? = null,
        val userData: UserData? = null,
        val geoAddress: GeoAddress? = null,
        val date: String? = null,
        val userId: String? = null
) {
    constructor(animal: SeenAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId
    )

    constructor(animal: TakenAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId
    )

    constructor(animal: LostAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId
    )
}
