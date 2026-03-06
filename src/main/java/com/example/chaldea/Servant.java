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
  private Long servantId;

  @Column(nullable = false)
  public String name;

  @Column(nullable = false, unique = true)
  private String type;

  private String species;
  private String origin;

  public Servant() {
  }

  public Servant(String name, String type, String species, String origin) {
    this.name = name;
    this.type = type;
    this.species = species;
    this.origin = origin;
  }

  public Servant(Long servantId, String name, String type, String species, String origin) {
    this.servantId = servantId;
    this.name = name;
    this.type = type;
    this.species = species;
    this.origin = origin;
  }

  public Long getServantId() {
    return servantId;
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