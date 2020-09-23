package nanicky.losties.losties.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserData(
        @Id val id: UUID,
        var name: String,
        var numbers: String,
        var emails: String,
        var networksUrls: String
)
