package com.personal.animals.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.personal.animals.enums.Gender;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.AnimalRepository;
import com.personal.animals.persitance.Breed;
import java.util.Collections;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalWithDetailsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AnimalRepository repository;

  @Test
  @SneakyThrows
  void getAnimalsWithDetailsTest() {
    repository.saveAllAndFlush(Collections.singletonList(getAnimal()));

    mockMvc.perform(get("/api/v1/animals-with-details/get-all-animals")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().json(
            """
                [
                  {
                    "id": 1,
                    "name": "Dog",
                    "age": 2,
                    "breedName": "Afganský chrt",
                    "gender": "FEMALE"
                  }
                ]
                """));
  }

  @NotNull
  private static Animal getAnimal() {
    Animal animal = new Animal();
    animal.setId(1);
    animal.setName("Dog");
    animal.setAge(2);
    animal.setGender(Gender.FEMALE);
    Breed breed = new Breed();
    breed.setId(1);
    animal.setBreed(breed);
    return animal;
  }
}