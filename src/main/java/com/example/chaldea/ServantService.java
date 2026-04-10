package com.example.chaldea;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServantService {

  private final ServantRepository servantRepository;          // ServantRepository to access methods and store servants

  private static final String UPLOAD_DIR = "src/main/resources/static/servant_pfp/";

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
          
          if(!updatedServant.getName().isBlank()){
            servant.setName(updatedServant.getName());
          }
          if(!updatedServant.getType().isBlank()){
            servant.setType(updatedServant.getType()); // updates servant using all parameters finding the designated servant via id
          }                                                         // and saves the updated servant into the repository
          if(!updatedServant.getSpecies().isBlank()){
            servant.setSpecies(updatedServant.getSpecies());
          }
          if(!updatedServant.getOrigin().isBlank()){
            servant.setOrigin(updatedServant.getOrigin());
          }
          if(!updatedServant.getDescription().isBlank()){
            servant.setDescription(updatedServant.getDescription());
          }
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

  public void saveProfilePicture(Servant servant, MultipartFile profilePicture) {
    if (profilePicture == null || profilePicture.isEmpty()) {
      return; // No picture uploaded, skip saving
    }
    String originalFileName = profilePicture.getOriginalFilename();
    try {
      if (originalFileName != null && originalFileName.contains(".")) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = String.valueOf(servant.getServantId()) + "." + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        InputStream inputStream = profilePicture.getInputStream();

        Files.createDirectories(Paths.get(UPLOAD_DIR));// Ensure directory exists
        Files.copy(inputStream, filePath,
            StandardCopyOption.REPLACE_EXISTING);// Save picture file
        servant.setProfilePicturePath(fileName);
        servantRepository.save(servant);// Update student with picture path
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}