package com.personal.animals.service;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.enums.Gender;
import org.springframework.stereotype.Service;

@Service
public class AnimalDtoValidationService {

  public void validate(AnimalDto animalDto) {
    validateGender(animalDto.gender());
  }

  private void validateGender(String gender) {
    try {
      Gender.valueOf(gender);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Allowed value for gender is: MALE or FEMALE");
    }
  }
}
