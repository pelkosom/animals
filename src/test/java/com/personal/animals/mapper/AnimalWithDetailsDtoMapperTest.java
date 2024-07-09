package com.personal.animals.mapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.personal.animals.dto.AnimalWithDetailsDto;
import com.personal.animals.enums.Gender;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.Breed;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

class AnimalWithDetailsDtoMapperTest {

  AnimalWithDetailsDtoMapper mapper = new AnimalWithDetailsDtoMapperImpl();

  @Test
  void mapToDtoTest() {
    Animal animal = getAnimal();

    AnimalWithDetailsDto animalWithDetailsDto = mapper.mapToDto(animal);


    assertThat(animalWithDetailsDto.id()).isEqualTo(animal.getId());
    assertThat(animalWithDetailsDto.age()).isEqualTo(animal.getAge());
    assertThat(animalWithDetailsDto.name()).isEqualTo(animal.getName());
    assertThat(animalWithDetailsDto.breedName()).isEqualTo(animal.getBreed().getName());
    assertThat(animalWithDetailsDto.gender()).isEqualTo(animal.getGender().toString());
  }

  @Test
  void mapToDtoListTest() {
    Animal animal = getAnimal();

    List<AnimalWithDetailsDto> animalWithDetailsDtos = mapper.mapToDto(Collections.singletonList(animal));

    assertThat(animalWithDetailsDtos).hasSize(1);
    AnimalWithDetailsDto animalWithDetailsDto = animalWithDetailsDtos.get(0);

    assertThat(animalWithDetailsDto.id()).isEqualTo(animal.getId());
    assertThat(animalWithDetailsDto.age()).isEqualTo(animal.getAge());
    assertThat(animalWithDetailsDto.name()).isEqualTo(animal.getName());
    assertThat(animalWithDetailsDto.breedName()).isEqualTo(animal.getBreed().getName());
    assertThat(animalWithDetailsDto.gender()).isEqualTo(animal.getGender().toString());
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