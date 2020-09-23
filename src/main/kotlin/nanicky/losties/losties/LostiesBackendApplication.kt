package nanicky.losties.losties

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LostiesBackendApplication

fun main(args: Array<String>) {
	runApplication<LostiesBackendApplication>(*args)
}
