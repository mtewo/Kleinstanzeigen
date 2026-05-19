package de.hsrm.mi.web.webprojekt.benutzer.ui;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("formularMap")
public class BenutzerController {

    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @ModelAttribute("formularMap")
    public Map<String,BenutzerFormular> initMap(){
        return new HashMap<>();
    }

    @GetMapping("/admin/benutzer/{loginname}")
    public String benutzerBearbeiten(@PathVariable String loginname, 
        @ModelAttribute("formularMap") Map <String, BenutzerFormular> formularMap, Model model) {

        logger.info("GET /admin/benutzer/{}", loginname);

        model.addAttribute("loginname", loginname);

        if(formularMap.containsKey(loginname)){
            BenutzerFormular formular = formularMap.get(loginname);
            model.addAttribute("formular", formular);

        } else {

        BenutzerFormular formular = new BenutzerFormular();
        formularMap.put(loginname, formular);
        
        model.addAttribute("formular", formular);

        }

        return "benutzer/bearbeiten";

    }

    @PostMapping("/admin/benutzer/{loginname}")
    public String postEingaben (@PathVariable String loginname, @Valid @ModelAttribute ("formular") BenutzerFormular formular, 
        BindingResult formularErrors, @ModelAttribute("formularMap") Map <String, BenutzerFormular> formularMap, 
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

        formularMap.put(loginname, formular);


        logger.info("POST formular: {}", formular.toString());


        return "benutzer/bearbeiten"; 
    }
    
    

}
