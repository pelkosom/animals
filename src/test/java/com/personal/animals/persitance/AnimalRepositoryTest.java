package com.personal.animals.persitance;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.personal.animals.enums.Gender;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles({"integration-test"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AnimalRepositoryTest {

  @Autowired
  private AnimalRepository animalRepository;

  @Test
  void persistTest() {
    Animal savedAnimal = animalRepository.save(getAnimal());

    List<Animal> entities = animalRepository.findAll();

    assertThat(entities).hasSize(1);
    assertThat(entities.get(0)).isEqualTo(savedAnimal);
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