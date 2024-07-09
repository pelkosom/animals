package com.personal.animals.mapper;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.personal.animals.persitance.Breed;
import com.personal.animals.persitance.BreedRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BreedMapperTest {

  @InjectMocks
  private BreedMapper breedMapper;
  @Mock
  private BreedRepository breedRepository;
  @Mock
  private Breed breed;

  @Test
  void breedIdToBreedTest() {
    when(breedRepository.findById(1L)).thenReturn(Optional.of(breed));

    Breed mapped = breedMapper.breedIdToBreed(1);

    assertThat(mapped).isEqualTo(breed);
  }

  @Test
  void breedIdToBreedThrowsTest() {
    when(breedRepository.findById(1L)).thenReturn(Optional.empty());

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> breedMapper.breedIdToBreed(1L));
    assertThat(exception).hasMessage("breedId 1 not found");
  }
}