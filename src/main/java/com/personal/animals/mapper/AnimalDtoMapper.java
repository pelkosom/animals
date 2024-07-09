package com.personal.animals.mapper;

import com.personal.animals.dto.AnimalDto;
import com.personal.animals.persitance.Animal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = BreedMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalDtoMapper {

  @Mapping(source = "breed.id", target = "breedId")
  AnimalDto mapToDto(Animal animal);

  List<AnimalDto> mapToDto(List<Animal> animalEntities);

  List<Animal> mapToEntity(List<AnimalDto> animals);

  @Mapping(source = "breedId", target = "breed", qualifiedByName = "breedIdToBreed")
  Animal mapToEntity(AnimalDto animalDto) throws IllegalArgumentException;
}
