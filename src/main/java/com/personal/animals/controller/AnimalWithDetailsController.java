package com.personal.animals.controller;

import com.personal.animals.dto.AnimalWithDetailsDto;
import com.personal.animals.service.AnimalWithDetailService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/animals-with-details")
@RequiredArgsConstructor
public class AnimalWithDetailsController {

  private final AnimalWithDetailService animalWithDetailService;

  @Operation(summary = "Get all animals with details")
  @GetMapping
  public ResponseEntity<List<AnimalWithDetailsDto>> getAnimalsWithDetails() {
    List<AnimalWithDetailsDto> animalsWithDetails = animalWithDetailService.getAnimalsWithDetails();
    return ResponseEntity.ok(animalsWithDetails);
  }
}
