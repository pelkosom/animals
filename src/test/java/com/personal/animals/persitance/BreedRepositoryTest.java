package com.personal.animals.persitance;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles({"integration-test"})
class BreedRepositoryTest {

  @Autowired
  private BreedRepository breedRepository;

  @Test
  void persistTest() {
    Breed breed = new Breed();
    breed.setName("Dog");
    breed.setId(1L);

    Breed savedBreed = breedRepository.save(breed);

    List<Breed> entities = breedRepository.findAll();

    assertThat(entities).hasSize(1);
    assertThat(entities.get(0)).isEqualTo(savedBreed);
  }
}