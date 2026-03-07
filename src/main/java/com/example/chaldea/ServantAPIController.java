package com.example.chaldea;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/servants")
public class ServantAPIController {

  private final ServantService servantService;

  public ServantAPIController(ServantService servantService) {
    this.servantService = servantService;
  }

  /**
   * Endpoint to retrieve all students.
   *
   * @return ResponseEntity containing a collection of all students.
   */
  @GetMapping("/")
  public ResponseEntity<Collection<Servant>> getAllServants() {
    return ResponseEntity.ok(servantService.getAllServants());
  }

  /**
   * Endpoint to retrieve a student by their ID.
   *
   * @param id The ID of the student to retrieve.
   * @return ResponseEntity containing the student if found, or a 404 Not Found
   *         status if not found.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Servant> getServantById(@PathVariable Long id) {
    Servant servant = servantService.getServantById(id);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Endpoint to create a new student.
   *
   * @param servant The student object to create, provided in the request body.
   * @return ResponseEntity containing the created student if successful, or a 404
   *         Not Found status if creation fails.
   */
  @PostMapping("/")
  public ResponseEntity<Servant> createStudent(@RequestBody Servant servant) {
    Servant createdServant = servantService.createServant(servant);
    if (createdServant != null) {
      return ResponseEntity.ok(createdServant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Endpoint to retrieve students by their major.
   *
   * @param type The major to filter students by, provided as a path variable.
   * @return ResponseEntity containing a collection of students with the specified
   *         major.
   */
  @GetMapping("/type/{type}")
  public ResponseEntity<Collection<Servant>> getServantsByType(@PathVariable String type) {
    return ResponseEntity.ok(servantService.getServantsByType(type));
  }

  /**
   * Endpoint to retrieve honors students based on a minimum GPA.
   *
   * @param origin The minimum GPA to filter honors students by, provided as a path
   *            variable.
   * @return ResponseEntity containing a collection of honors students with a GPA
   *         greater than or equal to the specified value.
   */
  @GetMapping("/origins/{origin}")
  public ResponseEntity<Collection<Servant>> getServantsByOrigin(@PathVariable String origin) {
    return ResponseEntity.ok(servantService.getServantsByOrigin(origin));
  }

  /**
   * Endpoint to search for students by name. If the name parameter is provided,
   * it will return students whose names contain the specified value. If the name
   * parameter is not provided, it will return all students.
   *
   * @param name The name to search for, provided as a request parameter. This
   *             parameter is optional.
   * @return ResponseEntity containing a collection of students that match the
   *         search criteria, or all students if no name is provided.
   */
  @GetMapping("/search")
  public ResponseEntity<Collection<Servant>> searchServantsByName(@RequestParam(required = false) String name) {
    List<Servant> servants;
    if (name != null) {
      servants = servantService.searchServantsByName(name);
    } else {
      servants = servantService.getAllServants();
    }
    return ResponseEntity.ok(servants);
  }

  /**
   * Endpoint to retrieve a student by their email address.
   *
   * @param species The email address of the student to retrieve, provided as a
   *              request parameter.
   * @return ResponseEntity containing the student if found, or a 404 Not Found
   *         status if not found.
   */
  @GetMapping("/species/{species}")
  public ResponseEntity<Servant> getServantBySpecies(@PathVariable String species) {
    Servant servant = servantService.getServantBySpecies(species);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Endpoint to update an existing student by their ID.
   *
   * @param id             The ID of the student to update, provided as a path
   *                       variable.
   * @param updatedServant The updated student object, provided in the request
   *                       body.
   * @return ResponseEntity containing the updated student if successful, or a 404
   *         Not Found status if the servant to update is not found.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Servant> updateServant(@PathVariable Long id, @RequestBody Servant updatedServant) {
    Servant servant = servantService.updateServant(id, updatedServant);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Endpoint to delete a student by their ID.
   *
   * @param id The ID of the student to delete, provided as a path variable.
   * @return ResponseEntity with no content if deletion is successful, or a 404
   *         Not Found status if the student to delete is not found.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServant(@PathVariable Long id) {
    servantService.deleteServant(id);
    return ResponseEntity.noContent().build();
  }

}