package nanicky.losties.losties.repo

import nanicky.losties.losties.entity.GeoAddress
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GeoAddressRepo : JpaRepository<GeoAddress, UUID>