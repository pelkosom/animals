package com.personal.animals.service;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.mapper.AnimalDtoMapper;
import com.personal.animals.persitance.Animal;
import com.personal.animals.persitance.AnimalRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  public ResponseEntity<AnimalDto> getAnimalById(long id) {
    return animalRepository.findById(id)
        .map(animalDtoMapper::mapToDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

//  TODO
  public boolean deleteAnimal(long id) {
    Optional<Animal> existingAnimal = animalRepository.findById(id);
    if (existingAnimal.isPresent()) {
      try {
        animalRepository.deleteById(existingAnimal.get().getId());
        return true;
      } catch (Exception e) {
        throw new IllegalStateException("Failed to delete animal: " + e.getMessage());
      }
    }
    return false;
  }


//  TODO co ak uz esxituje - duplicate id?
  public AnimalDto addAnimal(AnimalDto animalDto) throws IllegalArgumentException {
    animalDtoValidationService.validate(animalDto);
    Animal animal = animalDtoMapper.mapToEntity(animalDto);
    Animal savedAnimal = animalRepository.save(animal);
    return animalDtoMapper.mapToDto(savedAnimal);
  }

  public AnimalDto updateAnimal(AnimalDto animalDto) throws IllegalArgumentException {

//    animalRepository.findById(animalDto.get())

//    TODO exception from mapper is not thrown in api response
    Animal animal = animalDtoMapper.mapToEntity(animalDto);
    Animal savedAnimal = animalRepository.save(animal);
    return animalDtoMapper.mapToDto(savedAnimal);
  }
}
