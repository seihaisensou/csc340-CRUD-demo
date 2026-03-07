package com.example.chaldea;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServantService {

  private final ServantRepository servantRepository;

  public ServantService(ServantRepository servantRepository) {
    this.servantRepository = servantRepository;
  }

  public List<Servant> getAllServants() {
    return servantRepository.findAll();
  }

  public Servant createServant(Servant servant) {
    return servantRepository.save(servant);
  }

  public Servant getServantById(Long id) {
    return servantRepository.findById(id).orElse(null);
  }

  public Servant updateServant(Long id, Servant updatedServant) {
    return servantRepository.findById(id)
        .map(servant -> {
          servant.setName(updatedServant.getName());
          servant.setType(updatedServant.getType());
          servant.setSpecies(updatedServant.getSpecies());
          servant.setOrigin(updatedServant.getOrigin());
          return servantRepository.save(servant);
        })
        .orElse(null);
  }

  public void deleteServant(Long id) {
    servantRepository.deleteById(id);
  }

  public List<Servant> getServantsByType(String type) {
    return servantRepository.findByType(type);
  }

  public List<Servant> getServantsByOrigin(String origin) {
    return servantRepository.findbyOrigin(origin);
  }

  public List<Servant> searchServantsByName(String name) {
    return servantRepository.findByName(name);
  }

  public List<Servant> getServantBySpecies(String species) {
    return servantRepository.findBySpecies(species);
  }

}