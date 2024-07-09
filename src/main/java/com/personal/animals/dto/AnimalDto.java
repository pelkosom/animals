package com.personal.animals.dto;

import lombok.Builder;

@Builder
public record AnimalDto(
    long id,
    String name,
    int age,
    long breedId,
    String gender) {

}
