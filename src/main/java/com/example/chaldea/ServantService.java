package com.example.chaldea;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServantService {

  private final ServantRepository servantRepository;          // ServantRepository to access methods and store servants

  public ServantService(ServantRepository servantRepository) {
    this.servantRepository = servantRepository;
  }

  public List<Servant> getAllServants() {
    return servantRepository.findAll();           // returns all servants within the servant repository
  }

  public Servant createServant(Servant servant) {
    return servantRepository.save(servant);               // saves and stores servant into repository
  }

  public Servant getServantById(Long id) {
    return servantRepository.findById(id).orElse(null);     //  searches for and returns servant based on id
  }

  public Servant updateServant(Long id, Servant updatedServant) {
    return servantRepository.findById(id)
        .map(servant -> {
          servant.setName(updatedServant.getName());                // updates servant using all parameters finding the designated servant via id
          servant.setType(updatedServant.getType());                // and saves the updated servant into the repository
          servant.setSpecies(updatedServant.getSpecies());
          servant.setOrigin(updatedServant.getOrigin());
          return servantRepository.save(servant);
        })
        .orElse(null);
  }

  public void deleteServant(Long id) {
    servantRepository.deleteById(id);                           // repository uses the id to identify and delete the targeted servant
  }

  public List<Servant> getServantsByType(String type) {
    return servantRepository.findByType(type);                  // accesses method from ServantRepository to search for servants with a specific class type
  }

  public List<Servant> getServantsByOrigin(String origin) {
    return servantRepository.findbyOrigin(origin);              // same as above, accesses method of same name to search for servants that contain a certain origin
  }                                                             

  public List<Servant> searchServantsByName(String name) {      // same as aforementioned methods, accesses method from ServantRepository to identify servants that contain String name
    return servantRepository.findByName(name);
  }

  public List<Servant> getServantBySpecies(String species) {    // last method of the bunch, accesses same method from ServantRepository to find servants with a specific species.
    return servantRepository.findBySpecies(species);
  }

}