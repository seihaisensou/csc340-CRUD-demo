package com.example.chaldea;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServantRepository extends JpaRepository<Servant, Long> {

  List<Servant> findByType(String type);

  @Query(value = "SELECT s.* FROM servants s WHERE s.origin like %?1%", nativeQuery = true)
  List<Servant> findbyOrigin(String origin);

  @Query(value = "SELECT s.* FROM servants s WHERE s.name like %?1%", nativeQuery = true)
  List<Servant> findByName(String name);

  List<Servant> findBySpecies(String species);

}