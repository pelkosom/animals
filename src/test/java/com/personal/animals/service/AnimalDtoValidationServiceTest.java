package com.personal.animals.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.personal.animals.dto.AnimalDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalDtoValidationServiceTest {

  @InjectMocks
  private AnimalDtoValidationService animalDtoValidationService;

  @Test
  void validateDoesNotThrowTest() {
    AnimalDto animalDto = AnimalDto.builder()
        .id(1)
        .name("Dog")
        .age(2)
        .breedId(1)
        .gender("MALE")
        .build();

    assertDoesNotThrow(() -> animalDtoValidationService.validate(animalDto));
  }
  @Test
  void validateAgeThrowsTest() {
    AnimalDto animalDto = AnimalDto.builder()
        .id(1)
        .name("Dog")
        .age(0)
        .breedId(1)
        .gender("MALE")
        .build();

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> animalDtoValidationService.validate(animalDto));
    assertThat(exception).hasMessage("Age must be greater than 0");
  }

  @Test
  void validateNameThrowsTest() {
    AnimalDto animalDto = AnimalDto.builder()
        .id(1)
        .name(null)
        .age(3)
        .breedId(1)
        .gender("MALE")
        .build();

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> animalDtoValidationService.validate(animalDto));
    assertThat(exception).hasMessage("Name must not be null");
  }

  @Test
  void validateGenderThrowsTest() {
    AnimalDto animalDto = AnimalDto.builder()
        .id(1)
        .name("name")
        .age(3)
        .breedId(1)
        .gender("unknown")
        .build();

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> animalDtoValidationService.validate(animalDto));
    assertThat(exception).hasMessage("No enum constant com.personal.animals.enums.Gender.unknown");
  }

}