package com.personal.animals.service;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.mapper.AnimalDtoMapper;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.AnimalRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

  private final AnimalRepository animalRepository;
  private final AnimalDtoMapper animalDtoMapper;
  private final AnimalDtoValidationService animalDtoValidationService;

  public List<AnimalDto> getAnimals() {
    List<Animal> animalEntities = animalRepository.findAll();
    return animalDtoMapper.mapToDto(animalEntities);
  }

  public Optional<AnimalDto> getAnimalById(long id) {
    return animalRepository.findById(id)
        .map(animalDtoMapper::mapToDto);
  }

  public boolean deleteAnimal(long id) {
    Optional<Animal> existingAnimal = animalRepository.findById(id);
    if (existingAnimal.isPresent()) {
      animalRepository.deleteById(existingAnimal.get().getId());
      return true;
    }
    return false;
  }


  public AnimalDto addAnimal(AnimalDto animalDto) {
    Optional<Animal> existingAnimal = animalRepository.findById(animalDto.id());
    if (existingAnimal.isPresent()) {
      throw new IllegalArgumentException("Animal already exists with id: " + animalDto.id());
    }

    animalDtoValidationService.validate(animalDto);
    Animal animal = animalDtoMapper.mapToEntity(animalDto);
    Animal savedAnimal = animalRepository.save(animal);
    return animalDtoMapper.mapToDto(savedAnimal);
  }

  public Optional<AnimalDto> updateAnimal(AnimalDto animalDto) {
    Optional<Animal> existingAnimal = animalRepository.findById(animalDto.id());
    if (existingAnimal.isPresent()) {
      Animal animalEntity = animalDtoMapper.mapToEntity(animalDto);
      Animal updatedAnimalEntity = animalRepository.save(animalEntity);
      return Optional.of(animalDtoMapper.mapToDto(updatedAnimalEntity));
    }
    return Optional.empty();
  }
}
