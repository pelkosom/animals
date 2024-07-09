package com.personal.animals.persitance;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles({"integration-test"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BreedRepositoryTest {

  @Autowired
  private BreedRepository breedRepository;

  @Test
  void persistTest() {
    breedRepository.deleteAll();

    Breed breed = new Breed();
    breed.setName("Dog");
    breed.setId(1L);

    Breed savedBreed = breedRepository.save(breed);

    List<Breed> entities = breedRepository.findAll();

    assertThat(entities).hasSize(1);
    assertThat(entities.get(0)).isEqualTo(savedBreed);
  }
}