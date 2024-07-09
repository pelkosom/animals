package com.personal.animals.service;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.enums.Gender;
import org.springframework.stereotype.Service;

@Service
public class AnimalDtoValidationService {

  public void validate(AnimalDto animalDto) {
    validateAgeGreaterThanZero(animalDto.age());
    validateNameNotNull(animalDto.name());
    validateGender(animalDto.gender());
  }

  private void validateAgeGreaterThanZero(int age) {
    if (age <= 0) {
      throw new IllegalArgumentException("Age must be greater than 0");
    }
  }

  private void validateNameNotNull(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
  }

  private void validateGender(String gender) {
    Gender.valueOf(gender);
  }
}
