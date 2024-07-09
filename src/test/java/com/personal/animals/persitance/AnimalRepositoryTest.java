package com.personal.animals.persitance;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.personal.animals.enums.Gender;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles({"integration-test"})
class AnimalRepositoryTest {

  @Autowired
  private AnimalRepository animalRepository;

  @Autowired
  private BreedRepository breedRepository;

  @Test
  void persistTest() {
    Animal animal = getAnimal();

    Breed breed = new Breed();
    breed.setName("Dog");
    breed.setId(1L);

    breedRepository.save(breed);
    Animal savedAnimal = animalRepository.save(animal);

    List<Animal> entities = animalRepository.findAll();

    assertThat(entities).hasSize(1);
    assertThat(entities.get(0)).isEqualTo(savedAnimal);
  }

  @Test
  void persistThrowsTest() {
    Animal animal = getAnimal();

    JpaObjectRetrievalFailureException exception = assertThrows(JpaObjectRetrievalFailureException.class,
        () -> animalRepository.save(animal));
    assertThat(exception).hasMessage("Unable to find com.personal.animals.persitance.Breed with id 1");
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