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

 
  // Endpoint to retrieve all servants currently in the database, ResponseEntity contains a collection of all students.

  @GetMapping("/")
  public ResponseEntity<Collection<Servant>> getAllServants() {
    return ResponseEntity.ok(servantService.getAllServants());
  }
  
  // Endpoint utilzing the id parameter to retrieve a specific servant with that ID, returns the servant if found, otherwise returns 404 not found.
  
  @GetMapping("/{id}")
  public ResponseEntity<Servant> getServantById(@PathVariable Long id) {
    Servant servant = servantService.getServantById(id);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to create a new servant. ResponseEntity returns a container with the newly created Servant if successful, otherwise it returns 404 not found.

  @PostMapping("/")
  public ResponseEntity<Servant> createStudent(@RequestBody Servant servant) {
    Servant createdServant = servantService.createServant(servant);
    if (createdServant != null) {
      return ResponseEntity.ok(createdServant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint to retrieve servants of a specific class type, using String type, returns a collection of Servants with that class type.
  
  @GetMapping("/type/{type}")
  public ResponseEntity<Collection<Servant>> getServantsByType(@PathVariable String type) {
    return ResponseEntity.ok(servantService.getServantsByType(type));
  }

  // Endpoint to retrieve servants of a specific origin using String origin. Returns a collection of Servants belonging to that origin.
  
  @GetMapping("/origin/{origin}")
  public ResponseEntity<Collection<Servant>> getServantsByOrigin(@PathVariable String origin) {
    return ResponseEntity.ok(servantService.getServantsByOrigin(origin));
  }

  
  // Endpoint that searches for servants by name using String name. 
  // Returns a collection of those servants if found, if not, an empty container is shown.
  // If name is null, however, it just displays a collection of the servants in the database.

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

  
  // Endpoint that retrieves a collection of servants under a common species using String species. 
  // If there are servants that belong and exist to that race, they'll be shown. Otherwise, a 404 not found error will be shown.
  @GetMapping("/species/{species}")
  public ResponseEntity<Collection<Servant>> getServantBySpecies(@PathVariable String species) {
    List<Servant> servants = servantService.getServantBySpecies(species);
    if (servants != null) {
      return ResponseEntity.ok(servants);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Endpoint that updates a servant's parameters based on what you input. Retrieves the targeted servant using parameter id. 
  // Returns the updated servant or if the servant's id was invalid, returns a 404 not found error.

  @PutMapping("/{id}")
  public ResponseEntity<Servant> updateServant(@PathVariable Long id, @RequestBody Servant updatedServant) {
    Servant servant = servantService.updateServant(id, updatedServant);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Deletes a servant from the table by using it's designated id with the id parameter. Returns an empty container/body afterward.
 
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServant(@PathVariable Long id) {
    servantService.deleteServant(id);
    return ResponseEntity.noContent().build();
  }

}