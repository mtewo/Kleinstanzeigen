package de.hsrm.mi.web.webprojekt.benutzer.ui;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsrm.mi.web.webprojekt.benutzer.services.BenutzerService;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;
import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.webprojekt.entities.benutzer.mapper.BenutzerMapper;
import jakarta.validation.Valid;



@Controller
//@SessionAttributes("formularMap")
public class BenutzerController {

    private final BenutzerService benutzerService;
    private final BenutzerMapper mapper;

    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    public BenutzerController(BenutzerService benutzerSevice, BenutzerMapper mapper){
        this.benutzerService = benutzerSevice;
        this.mapper = mapper;
    }

   /*  @ModelAttribute("formularMap")
    public Map<String,BenutzerFormular> initMap(){
        return new HashMap<>();
    }*/
    @Transactional
    @GetMapping("/admin/benutzer/{loginname}")
    public String benutzerBearbeiten(@PathVariable String loginname, 
       /*  @ModelAttribute("formularMap") Map <String, BenutzerFormular> formularMap,*/ Model model) {

        logger.info("GET /admin/benutzer/{}", loginname);

        model.addAttribute("loginname", loginname);

        Optional<Benutzer> benutzerOpt = benutzerService.findBenutzerById(loginname);

        if(benutzerOpt.isPresent()){
            Benutzer benutzer = benutzerOpt.get();
            BenutzerFormular formular = mapper.benutzerToBenutzerFormular(benutzer);
            model.addAttribute("formular", formular);

            // Bestellungen alphabetisch sortiert ins Model
            List<Anzeige> bestellungen = benutzer.getBestellungen()
            .stream()
            .sorted(Comparator.comparing(Anzeige::getTitel))
            .collect(Collectors.toList());
            model.addAttribute("bestellungen", bestellungen);

        } else {

            BenutzerFormular formular = new BenutzerFormular();
            model.addAttribute("formular", formular);

        }
        

        /*if(formularMap.containsKey(loginname)){
            BenutzerFormular formular = formularMap.get(loginname);
            model.addAttribute("formular", formular);

        } else {*/

        //BenutzerFormular formular = new BenutzerFormular();
        //formularMap.put(loginname, formular);
        
        //model.addAttribute("formular", formular);

        

        return "benutzer/bearbeiten";

    }

    @PostMapping("/admin/benutzer/{loginname}")
    public String postEingaben (@PathVariable String loginname, @Valid @ModelAttribute ("formular") BenutzerFormular formular, 
        BindingResult formularErrors, //@ModelAttribute("formularMap") Map <String, BenutzerFormular> formularMap, 
        Model model){

        model.addAttribute("loginname", loginname);
        //model.addAttribute("formular", formular);

        if (!formular.getPasswort().isEmpty() || !formular.getPasswortWiederholung().isEmpty()){
            
            if(!formular.getPasswort().equals(formular.getPasswortWiederholung())){

                formularErrors.rejectValue(
                    "passwortWiederholung",           
                    "benutzer.fehler.passwortwiederholung",  
                    "Passworteingaben stimmen nicht überein" 
                );

            }
        }

        if (formularErrors.hasErrors()) {
            logger.info("POST Validierungsfehler: {}", formularErrors.getAllErrors());
            return "benutzer/bearbeiten";
        }


        try {
        Benutzer benutzer = mapper.benutzerFormularToBenutzer(formular);
        benutzer. setLoginName(loginname);

        // Passwort SOnderfall
        if(benutzer.getPasswort().isEmpty()){
            Optional<Benutzer> vorhandener = benutzerService.findBenutzerById(loginname);
            if (vorhandener.isPresent()) {
                // altes Passwort übernehmen
                benutzer.setPasswort(vorhandener.get().getPasswort());
            }else{
                throw new BenutzerException("new user without a password");
            }
        }

            benutzerService.saveBenutzer(benutzer);

        } catch ( BenutzerException e) {
            logger.error("Fehler beim Speichern: {}", e.getMessage());
            model.addAttribute("info", "Problem:" + e.getMessage());
            return "benutzer/bearbeiten";
        } catch (Exception e) {
            logger.error("Speicherfehler: {}", e.getMessage());
            model.addAttribute("info", "Speicherproblem: " + e.getMessage());
            return "benutzer/bearbeiten";
        }


        //formularMap.put(loginname, formular);


        logger.info("POST formular: {}", formular.toString());


        return "redirect:/admin/benutzer"; 
    }

    @GetMapping("/admin/benutzer")
    public String beuntzerliste(Model model) {
        Collection<Benutzer> benutzerliste = benutzerService.findAllBenutzer();

        model.addAttribute("benutzerliste", benutzerliste);

        return "benutzer/liste";

    }

    @GetMapping("/admin/benutzer/{loginname}/delete")
    public String benutzerLoeschen(@PathVariable String loginname){

        logger.info("GET delete {}", loginname);

        benutzerService.deleteBenutzerById(loginname);

        return "redirect:/admin/benutzer";
    }

    
    
    

}
