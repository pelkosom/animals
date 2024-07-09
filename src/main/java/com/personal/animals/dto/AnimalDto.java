package com.personal.animals.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AnimalDto(
    @NotNull
    Long id,
    @NotBlank
    String name,
    @NotNull
    @Positive
    Integer age,
    @NotNull
    Long breedId,
    @NotBlank
    String gender) {

}
