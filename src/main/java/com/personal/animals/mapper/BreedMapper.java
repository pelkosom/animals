package com.personal.animals.mapper;

import com.personal.animals.persitance.Breed;
import com.personal.animals.persitance.BreedRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreedMapper {

  private final BreedRepository breedRepository;

  @Named("breedIdToBreed")
  public Breed breedIdToBreed(long breedId) {
    return breedRepository.findById(breedId)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("breedId %s not found", breedId)));
  }
}
