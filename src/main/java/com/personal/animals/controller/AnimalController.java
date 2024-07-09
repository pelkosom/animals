package com.personal.animals.controller;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/animals/")
@RequiredArgsConstructor
public class AnimalController {

  private final AnimalService animalService;

  @Operation(summary = "Creates new animal")
  @PostMapping
  public ResponseEntity<AnimalDto> addAnimal(@RequestBody @Valid AnimalDto animalDto) {
    AnimalDto createdAnimal = animalService.addAnimal(animalDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdAnimal);
  }

  @Operation(summary = "Deletes existing animal by id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeAnimal(@PathVariable long id) {
    if (animalService.deleteAnimal(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Get animal by id")
  @GetMapping("/{id}")
  public ResponseEntity<AnimalDto> getAnimal(@PathVariable long id) {
    return animalService.getAnimalById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Update animal")
  @PutMapping
  public ResponseEntity<AnimalDto> updateAnimal(@RequestBody @Valid AnimalDto animalDto) {
    Optional<AnimalDto> updatedAnimalDto = animalService.updateAnimal(animalDto);
    return updatedAnimalDto
        .map(dto -> ResponseEntity.ok().body(dto))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Get all animals")
  @GetMapping
  public ResponseEntity<List<AnimalDto>> getAnimals() {
    return ResponseEntity.ok(animalService.getAnimals());
  }
}
