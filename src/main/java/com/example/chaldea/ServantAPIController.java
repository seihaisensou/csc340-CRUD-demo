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

 
  @GetMapping("/")
  public ResponseEntity<Collection<Servant>> getAllServants() {
    return ResponseEntity.ok(servantService.getAllServants());
  }

  
  @GetMapping("/{id}")
  public ResponseEntity<Servant> getServantById(@PathVariable Long id) {
    Servant servant = servantService.getServantById(id);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  
  @PostMapping("/")
  public ResponseEntity<Servant> createStudent(@RequestBody Servant servant) {
    Servant createdServant = servantService.createServant(servant);
    if (createdServant != null) {
      return ResponseEntity.ok(createdServant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  
  @GetMapping("/type/{type}")
  public ResponseEntity<Collection<Servant>> getServantsByType(@PathVariable String type) {
    return ResponseEntity.ok(servantService.getServantsByType(type));
  }

  
  @GetMapping("/origin/{origin}")
  public ResponseEntity<Collection<Servant>> getServantsByOrigin(@PathVariable String origin) {
    return ResponseEntity.ok(servantService.getServantsByOrigin(origin));
  }

  
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

  
  @GetMapping("/species/{species}")
  public ResponseEntity<Collection<Servant>> getServantBySpecies(@PathVariable String species) {
    List<Servant> servants = servantService.getServantBySpecies(species);
    if (servants != null) {
      return ResponseEntity.ok(servants);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  
  @PutMapping("/{id}")
  public ResponseEntity<Servant> updateServant(@PathVariable Long id, @RequestBody Servant updatedServant) {
    Servant servant = servantService.updateServant(id, updatedServant);
    if (servant != null) {
      return ResponseEntity.ok(servant);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

 
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteServant(@PathVariable Long id) {
    servantService.deleteServant(id);
    return ResponseEntity.noContent().build();
  }

}