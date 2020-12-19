package nanicky.losties.losties.repo

import nanicky.losties.losties.model.UserData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserDataRepo  : JpaRepository<UserData, UUID>