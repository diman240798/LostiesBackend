package nanicky.losties.losties.model

import nanicky.losties.losties.entity.GeoAddress
import nanicky.losties.losties.entity.LostAnimal
import nanicky.losties.losties.entity.SeenAnimal
import nanicky.losties.losties.entity.TakenAnimal
import nanicky.losties.losties.enums.PublicationTypes
import java.util.*

class AnimalPublicationTyped(
        id: UUID? = null,
        animal: Animal? = null,
        userData: UserData? = null,
        geoAddress: GeoAddress? = null,
        date: String? = null,
        userId: String? = null,
        val publicationTypes: String
) : AnimalPublication(id, animal, userData, geoAddress, date, userId) {
    constructor(animal: SeenAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId, PublicationTypes.SEEN.name
    )
    constructor(animal: TakenAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId, PublicationTypes.TAKEN.name
    )
    constructor(animal: LostAnimal) : this(
            animal.id, animal.animal, animal.userData, animal.geoAddress, animal.date.toString(), animal.userId, PublicationTypes.LOST.name
    )
}