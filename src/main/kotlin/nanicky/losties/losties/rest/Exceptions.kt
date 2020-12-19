package nanicky.losties.losties.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "publication not found")
class PublicationNotFoundException : RuntimeException()