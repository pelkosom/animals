package com.personal.animals.dto;

public record AnimalWithDetailsDto(
    long id,
    String name,
    int age,
    String breedName,
    String gender) {

}
