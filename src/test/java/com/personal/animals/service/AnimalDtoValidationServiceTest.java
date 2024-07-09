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
        .id(1L)
        .name("Dog")
        .age(2)
        .breedId(1L)
        .gender("MALE")
        .build();

    assertDoesNotThrow(() -> animalDtoValidationService.validate(animalDto));
  }

  @Test
  void validateGenderThrowsTest() {
    AnimalDto animalDto = AnimalDto.builder()
        .id(1L)
        .name("name")
        .age(3)
        .breedId(1L)
        .gender("unknown")
        .build();

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> animalDtoValidationService.validate(animalDto));
    assertThat(exception).hasMessage("Allowed value for gender is: MALE or FEMALE");
  }

}