package nanicky.losties.losties

import nanicky.losties.losties.entity.GeoAddress
import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.model.UserData

class AddAnimalRequest(
        var animal: Animal? = null,
        val userData: UserData? = null,
        val geoAddress: GeoAddress? = null,
        val photos: List<ByteArray>,
        val userId: String? = null
)
