package com.example.chaldea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servants")
public class Servant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long servantId;                                 // ID differentiating servants, a unique one is generated when a new servant is created

  @Column(nullable = false)
  public String name;

  @Column(nullable = false, unique = false)
  private String type;                                   // Class is a protected name by java so servant.type is supposed to represent the Servant's class
  private String species;                                // The servant's species
  private String origin;                                 // The series they originate from

  public Servant() {
  }

  public Servant(String name, String type, String species, String origin) {     // Constructor of a Servant type object
    this.name = name;
    this.type = type;
    this.species = species;
    this.origin = origin;
  }

  public Long getServantId() {
    return servantId;                                   //  Get and Set methods to easily change and access parameters of the Servant object
  }

  public void setServantId(Long servantId) {
    this.servantId = servantId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

}