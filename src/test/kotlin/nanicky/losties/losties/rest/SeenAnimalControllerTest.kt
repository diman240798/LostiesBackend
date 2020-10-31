package nanicky.losties.losties.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import nanicky.losties.losties.MapperWithDates
import nanicky.losties.losties.entity.SeenAnimal
import nanicky.losties.losties.model.Animal
import nanicky.losties.losties.repo.AnimalRepo
import nanicky.losties.losties.repo.SeenAnimalRepo
import nanicky.losties.losties.util.AnimalType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.util.*


@SpringBootTest
@AutoConfigureMockMvc
class SeenAnimalControllerTest {


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repo: SeenAnimalRepo

    @Autowired
    lateinit var repoAnimal: AnimalRepo

    @BeforeEach
    fun init() {
        repo.deleteAll()
    }

    @Test
    fun getListEmpty() {

        val response = mockMvc.perform(get("/seen/get"))
                .andExpect { status().isOk }
                .andReturn()

        val mapper = ObjectMapper()

        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertTrue(animalsList.isEmpty())
    }

    @Test
    fun getListAfterDate() {

        val today = LocalDate.now()
        val yesterday = LocalDate.now().minusDays(1)

        var SeenAnimal = SeenAnimal(id = UUID.randomUUID(), date = today)
        SeenAnimal = repo.save(SeenAnimal)

        val request = get("/seen/get").queryParam("dateStart", yesterday.toString())
        val response = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()

        val mapper = MapperWithDates()

        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal, animalsList[0])
    }

    @Test
    fun getListBeforeDate() {

        val today = LocalDate.now()
        val tomorrow = LocalDate.now().plusDays(1)

        var SeenAnimal = SeenAnimal(id = UUID.randomUUID(), date = tomorrow)
        SeenAnimal = repo.save(SeenAnimal)

        val request = get("/seen/get").queryParam("dateStart", today.toString())
        val response = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()

        val mapper = MapperWithDates()

        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal, animalsList[0])
    }

    @Test
    fun getListBetweenDatesDate() {

        val today = LocalDate.now()
        val tomorrow = LocalDate.now().plusDays(1)
        val yesterday = LocalDate.now().minusDays(1)

        var SeenAnimal = SeenAnimal(id = UUID.randomUUID(), date = today)
        SeenAnimal = repo.save(SeenAnimal)


        val mapper = MapperWithDates()

        val request = get("/seen/get")
                .queryParam("dateStart", yesterday.toString())
                .queryParam("dateEnd", tomorrow.toString())
        val response = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()


        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal, animalsList[0])


        val requestStartToday = get("/seen/get")
                .queryParam("dateStart", today.toString())
                .queryParam("dateEnd", tomorrow.toString())
        val responseStartToday = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()


        val animalsListStartToday = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsListStartToday.size)
        Assertions.assertEquals(SeenAnimal, animalsListStartToday[0])


        val requestEndToday = get("/seen/get")
                .queryParam("dateStart", yesterday.toString())
                .queryParam("dateEnd", today.toString())
        val responseEndToday = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()


        val animalsListEndToday = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsListEndToday.size)
        Assertions.assertEquals(SeenAnimal, animalsListEndToday[0])

        val requestTodayToday = get("/seen/get")
                .queryParam("dateStart", today.toString())
                .queryParam("dateEnd", today.toString())
        val responseTodayToday = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()

        val animalsListTodayToday = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal, animalsList[0])
    }

    @Test
    fun getListBreed() {

        val breed = "American"
        var animal = Animal(id = UUID.randomUUID(), breed = breed)
        animal = repoAnimal.save(animal)
        var SeenAnimal = SeenAnimal(id = UUID.randomUUID(), animal = animal)
        SeenAnimal = repo.save(SeenAnimal)

        val request = get("/seen/get").queryParam("breed", breed)
        val response = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()

        val mapper = MapperWithDates()

        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal.animal!!.breed, animalsList[0].animal!!.breed)
    }

    @Test
    fun getListType() {

        val type = AnimalType.CAT
        var animal = Animal(id = UUID.randomUUID(), type = type)
        animal = repoAnimal.save(animal)
        var SeenAnimal = SeenAnimal(id = UUID.randomUUID(), animal = animal)
        SeenAnimal = repo.save(SeenAnimal)

        val request = get("/seen/get").queryParam("type", type.name)
        val response = mockMvc.perform(request)
                .andExpect { status().isOk }
                .andReturn()

        val mapper = MapperWithDates()

        val animalsList = mapper.readValue(response.response.contentAsString, object : TypeReference<List<SeenAnimal>>() {})
        Assertions.assertEquals(1, animalsList.size)
        Assertions.assertEquals(SeenAnimal.animal!!.type, animalsList[0].animal!!.type)
    }
}