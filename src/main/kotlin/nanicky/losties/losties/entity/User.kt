package nanicky.losties.losties.entity

import nanicky.losties.losties.enums.PublicationTypes
import nanicky.losties.losties.model.UserData
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Id
import javax.persistence.OneToOne

data class User(
        @Id val id: UUID,
        @OneToOne val userData: UserData,
        @ElementCollection val publications: MutableMap<PublicationTypes, UUID>
)