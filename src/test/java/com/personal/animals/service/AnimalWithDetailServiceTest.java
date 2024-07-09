package com.personal.animals.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.personal.animals.dto.AnimalWithDetailsDto;
import com.personal.animals.mapper.AnimalWithDetailsDtoMapper;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.AnimalRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnimalWithDetailServiceTest {

  @InjectMocks
  private AnimalWithDetailService animalWithDetailService;
  @Mock
  private AnimalRepository animalRepository;
  @Mock
  private AnimalWithDetailsDtoMapper animalWithDetailsDtoMapper;
  @Mock
  private List<Animal> animalEntities;
  @Mock
  private List<AnimalWithDetailsDto> animalsWithDetails;

  @Test
  void getAnimalsWithDetailsTest() {
    when(animalRepository.findAll()).thenReturn(animalEntities);
    when(animalWithDetailsDtoMapper.mapToDto(animalEntities)).thenReturn(animalsWithDetails);

    List<AnimalWithDetailsDto> results = animalWithDetailService.getAnimalsWithDetails();

    assertThat(results).isEqualTo(animalsWithDetails);
  }
}