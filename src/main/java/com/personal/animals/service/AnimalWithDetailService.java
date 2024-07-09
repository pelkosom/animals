package com.personal.animals.service;

import com.personal.animals.dto.AnimalWithDetailsDto;
import com.personal.animals.mapper.AnimalWithDetailsDtoMapper;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.AnimalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalWithDetailService {

  private final AnimalRepository animalRepository;
  private final AnimalWithDetailsDtoMapper animalWithDetailsDtoMapper;

  public List<AnimalWithDetailsDto> getAnimalsWithDetails() {
    List<Animal> animalEntities = animalRepository.findAll();
    return animalWithDetailsDtoMapper.mapToDto(animalEntities);
  }
}
