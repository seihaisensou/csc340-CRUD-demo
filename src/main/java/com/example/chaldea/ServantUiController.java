package com.example.chaldea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/servants")
public class ServantUiController {

    @Autowired
    private ServantService servantService;

    

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/add")
    public String showAddServantForm(Model model) {
    model.addAttribute("servant", new Servant());
    model.addAttribute("title", "Summon a new servant");
    return "new-character-form";
  }

    @GetMapping("/{characterId}")
    public String showDetailsWithId(@PathVariable long characterId, Model model){
        Servant servant = servantService.getServantById(characterId);
        model.addAttribute("foundCharacter", servant);
        if(servant != null){
            return "details";
        }
        else{
            return "about";
        }
    }

    @GetMapping ("/all")
    public String getAllCharacters(Model model){
        model.addAttribute("servantRoster", servantService.getAllServants());
        return "index";
    }

    @GetMapping ("/all/{type}")
    public String getAllCharactersbyClass(Model model, @PathVariable String type){
        model.addAttribute("servantRoster", servantService.getServantsByType(type));
        return "index";
    }

    @PostMapping("/")
    public String addServant(Servant servant, MultipartFile picture) {
    Servant newServant = servantService.createServant(servant);
    if (newServant != null) {
      servantService.saveProfilePicture(newServant, picture);
      return "redirect:/servants/" + newServant.getServantId();
    } 
    else {
      return "redirect:/servants/add?error=true";
        }
    }

    @PostMapping("/update/{id}")
    public String updateServant(@PathVariable Long id, Servant updatedServant, MultipartFile picture) {
    Servant servant = servantService.updateServant(id, updatedServant);
    if (servant != null) {
      servantService.saveProfilePicture(servant, picture);
      return "redirect:/servants/" + servant.getServantId();
    } 
    else {
      return "redirect:/servants/update/" + id + "?error=true";
        }
    }

    @GetMapping("/updateForm/{id}")
    public Object showUpdateForm(@PathVariable Long id, Model model) {
    Servant servant= servantService.getServantById(id);
    model.addAttribute("foundCharacter", servant);
    model.addAttribute("title", "Alter a servant's saint graph" + id);
    return "update-servant";
  }

    @GetMapping("/delete/{id}")
    public String deleteServant(@PathVariable Long id) {
        servantService.deleteServant(id);
        return "redirect:/servants/all";
    }
    
}
