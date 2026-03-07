package com.example.chaldea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant, Long> {

  // findByType uses String type to search for servants with that exact class type.

  List<Servant> findByType(String type);

  // findByOrigin uses String origin to find any servants with an origin containing that String.

  @Query(value = "SELECT s.* FROM servants s WHERE s.origin like %?1%", nativeQuery = true)
  List<Servant> findbyOrigin(String origin);

  // findByName uses String name in the same way that findByOrigin does, it finds any servants that contain String name in it.

  @Query(value = "SELECT s.* FROM servants s WHERE s.name like %?1%", nativeQuery = true)
  List<Servant> findByName(String name);

  // findBySpecies finds servants who are of a species that exactly matches the parameter String species.

  List<Servant> findBySpecies(String species);

}