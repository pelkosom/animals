package com.personal.animals.mapper;

import com.personal.animals.dto.AnimalWithDetailsDto;
import com.personal.animals.persitance.Animal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = BreedMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalWithDetailsDtoMapper {

  @Mapping(source = "breed.name", target = "breedName")
  AnimalWithDetailsDto mapToDto(Animal animal);

  List<AnimalWithDetailsDto> mapToDto(List<Animal> animalEntities);
}