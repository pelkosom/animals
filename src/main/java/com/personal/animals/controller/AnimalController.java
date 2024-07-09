package com.personal.animals.controller;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

  private final AnimalService animalService;

  @Operation(summary = "Creates new animal")
  @PostMapping(value = "/add-animal")
  public ResponseEntity<AnimalDto> addAnimal(@RequestBody AnimalDto animalDto) {
    try {
      AnimalDto createdAnimal = animalService.addAnimal(animalDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity("Failed to create animal: " + e.getMessage(),
          HttpStatus.BAD_REQUEST);
    }
  }

  @Operation(summary = "Deletes existing animal by id")
  @DeleteMapping("/delete-animal/{id}")
  public ResponseEntity<Void> removeAnimal(@PathVariable long id) {
    if (animalService.deleteAnimal(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Get animal by id")
  @GetMapping("/animal/{id}")
  public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
    return animalService.getAnimalById(id);
  }

  @Operation(summary = "Update animal")
  @PutMapping(value = "/update-animal")
  public ResponseEntity<AnimalDto> updateAnimal(@RequestBody AnimalDto animalDto) {
    try {
      Optional<AnimalDto> updatedAnimalDto = animalService.updateAnimal(animalDto);
      return updatedAnimalDto
          .map(dto -> ResponseEntity.ok().body(dto))
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (IllegalArgumentException e) {
      return new ResponseEntity("Failed to update animal: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Get all animals")
  @GetMapping(value = "/get-all-animals")
  public ResponseEntity<List<AnimalDto>> getAnimals() {

    List<AnimalDto> animals = animalService.getAnimals();

    if (animals.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(animals);
  }
}
