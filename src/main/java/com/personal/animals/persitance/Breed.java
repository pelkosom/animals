package com.personal.animals.persitance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "breed")
@Data
public class Breed {

  @Id
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

}
