package com.personal.animals.mapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.enums.Gender;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.Breed;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalDtoMapperTest {

  @InjectMocks
  private AnimalDtoMapperImpl animalDtoMapper;
  @Mock
  private BreedMapper breedMapper;
  @Mock
  private Breed breed;

  @Test
  void mapToDtoTest() {
    Animal animal = getAnimal();
    AnimalDto animalDto = animalDtoMapper.mapToDto(animal);

    assertThat(animalDto.id()).isEqualTo(animal.getId());
    assertThat(animalDto.age()).isEqualTo(animal.getAge());
    assertThat(animalDto.name()).isEqualTo(animal.getName());
    assertThat(animalDto.breedId()).isEqualTo(animal.getBreed().getId());
    assertThat(animalDto.gender()).isEqualTo(animal.getGender().toString());
  }

  @Test
  void mapToDtoListTest() {
    Animal animal = getAnimal();
    List<AnimalDto> animalDtos = animalDtoMapper.mapToDto(Collections.singletonList(animal));

    assertThat(animalDtos).hasSize(1);
    AnimalDto animalDto = animalDtos.get(0);
    assertThat(animalDto.id()).isEqualTo(animal.getId());
    assertThat(animalDto.age()).isEqualTo(animal.getAge());
    assertThat(animalDto.name()).isEqualTo(animal.getName());
    assertThat(animalDto.breedId()).isEqualTo(animal.getBreed().getId());
    assertThat(animalDto.gender()).isEqualTo(animal.getGender().toString());
  }

  @Test
  void mapToEntityTest() {
    when(breedMapper.breedIdToBreed(1)).thenReturn(breed);

    AnimalDto animalDto = getAnimalDto();
    Animal animal = animalDtoMapper.mapToEntity(animalDto);

    assertThat(animal.getId()).isEqualTo(animalDto.id());
    assertThat(animal.getAge()).isEqualTo(animalDto.age());
    assertThat(animal.getName()).isEqualTo(animalDto.name());
    assertThat(animal.getBreed().getId()).isEqualTo(breed.getId());
    assertThat(animal.getGender()).isEqualTo(Gender.valueOf(animalDto.gender()));
  }

  @Test
  void mapToEntityListTest() {
    when(breedMapper.breedIdToBreed(1)).thenReturn(breed);

    AnimalDto animalDto = getAnimalDto();
    List<Animal> animalEntities = animalDtoMapper.mapToEntity(Collections.singletonList(animalDto));

    assertThat(animalEntities).hasSize(1);
    Animal animal = animalEntities.get(0);
    assertThat(animal.getId()).isEqualTo(animalDto.id());
    assertThat(animal.getAge()).isEqualTo(animalDto.age());
    assertThat(animal.getName()).isEqualTo(animalDto.name());
    assertThat(animal.getBreed().getId()).isEqualTo(breed.getId());
    assertThat(animal.getGender()).isEqualTo(Gender.valueOf(animalDto.gender()));
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


  @NotNull
  private static AnimalDto getAnimalDto() {
    return AnimalDto.builder()
        .gender("MALE")
        .breedId(1L)
        .id(1L)
        .age(2)
        .name("Dog")
        .age(2)
        .build();
  }
}