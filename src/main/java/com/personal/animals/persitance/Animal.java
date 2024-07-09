package com.personal.animals.persitance;

import com.personal.animals.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "animal")
@Data
public class Animal {

  @Id
  @Column(nullable = false)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private int age;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @ManyToOne
  @JoinColumn(name = "breed_id", nullable = false, foreignKey = @ForeignKey(name = "fk_animal_breed"))
  private Breed breed;

}
