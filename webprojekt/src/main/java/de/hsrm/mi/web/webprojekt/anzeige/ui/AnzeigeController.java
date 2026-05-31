package de.hsrm.mi.web.webprojekt.anzeige.ui;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsrm.mi.web.webprojekt.anzeige.services.AnzeigeService;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;
import de.hsrm.mi.web.webprojekt.entities.anzeige.mapper.AnzeigeMapper;
import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.webprojekt.benutzer.services.BenutzerService;
import jakarta.validation.Valid;

@Controller
public class AnzeigeController {

    Logger logger = LoggerFactory.getLogger(AnzeigeController.class);
    private final AnzeigeService anzeigeService;
    private final AnzeigeMapper mapper;
    private final BenutzerService benutzerService;


     public AnzeigeController(AnzeigeService anzeigeService, AnzeigeMapper mapper, BenutzerService benutzerService) {
        this.anzeigeService = anzeigeService;
        this.mapper = mapper;
        this.benutzerService = benutzerService;
    }

    @GetMapping("/admin/anzeige")
    public String anzeigeliste(Model model) {
        Collection<Anzeige> anzeigeliste = anzeigeService.findAllAnzeigen();
        model.addAttribute("anzeigeliste", anzeigeliste);
        return "anzeige/liste";
    }

    @GetMapping("admin/anzeige/{id}")
    public String bearbeiteAnzeige(@PathVariable long id, Model model){
        logger.info("Anzeige id", id);

         
        model.addAttribute("anzeigeid", id);
 
        if (id == 0) {
            // Neue Anzeige
            model.addAttribute("formular", new AnzeigeFormular());
        } else {
            
            Optional<Anzeige> anzeigeOpt = anzeigeService.findAnzeigeById(id);
            
            if (anzeigeOpt.isPresent()) {
                
               Anzeige anzeige = anzeigeOpt.get();

                model.addAttribute("formular", mapper.anzeigeToAnzeigeFormular(anzeige));

                model.addAttribute("anzeige", anzeige);

            
            } else {
                
                model.addAttribute("formular", new AnzeigeFormular());
                
                model.addAttribute("info", "Anzeige mit ID " + id + " nicht gefunden.");
            }
        }
        return "anzeige/bearbeiten";
        
    }

    @PostMapping("/admin/anzeige/{id}")
    public String postAnzeige(@PathVariable long id,
            @Valid @ModelAttribute("formular") AnzeigeFormular formular, BindingResult formularErrors,
            Model model) {
 
        logger.info("POST /admin/anzeige/{} formular: {}", id, formular);
        model.addAttribute("anzeigeid", id);
 
        if (formularErrors.hasErrors()) {
            logger.info("POST Validierungsfehler: {}", formularErrors.getAllErrors());
            return "anzeige/bearbeiten";
        }
 
        try {
            Anzeige anzeige = mapper.anzeigeFormularToAnzeige(formular);
 
            if (id != 0) {
                anzeige.setId(id);
                // Beziehungen aus vorhandener Entität übernehmen
                Anzeige vorhandene = anzeigeService.findAnzeigeById(id)
                            .orElseThrow(() -> new AnzeigeException("Anzeige mit ID " + id + " nicht gefunden"));
                    anzeige.setAnbieter(vorhandene.getAnbieter());
                    anzeige.setBesteller(vorhandene.getBesteller());
                }
 
            anzeigeService.saveAnzeige(anzeige);
 
        } catch (AnzeigeException e) {
            logger.error("Fehler beim Speichern: {}", e.getMessage());
            model.addAttribute("info", "Problem: " + e.getMessage());
            return "anzeige/bearbeiten";
        } catch (Exception e) {
            logger.error("Speicherfehler: {}", e.getMessage());
            model.addAttribute("info", "Speicherproblem: " + e.getMessage());
            return "anzeige/bearbeiten";
        }
 
        return "redirect:/admin/anzeige";
    }
 
    @GetMapping("/admin/anzeige/{id}/delete")
    public String anzeigeLoeschen(@PathVariable long id) {
        logger.info("GET delete anzeige {}", id);
        anzeigeService.deleteAnzeigeById(id);
        return "redirect:/admin/anzeige";

    
    }

    @GetMapping("/admin/anzeige/{anzeigeid}/bestellen/{loginname}")
    public String bestellen(@PathVariable long anzeigeid, @PathVariable String loginname, Model model){

        logger.info("GET /admin/anzeige/{}/bestellen/{}", anzeigeid, loginname);

        try {
            Anzeige anzeige = anzeigeService.findAnzeigeById(anzeigeid)
                    .orElseThrow(() -> new AnzeigeException("Anzeige mit ID " + anzeigeid + " nicht gefunden"));

            Benutzer benutzer = benutzerService.findBenutzerById(loginname)
                    .orElseThrow(() -> new AnzeigeException("Benutzer " + loginname + " nicht gefunden"));

            anzeigeService.bestellen(anzeige, benutzer);

        } catch (AnzeigeException e) {
            logger.error("Fehler beim Bestellen: {}", e.getMessage());
            model.addAttribute("info", e.getMessage());
            return "redirect:/admin/anzeige";
        }

        return "redirect:/admin/anzeige";
    }

    @GetMapping("/admin/anzeige/{anzeigeid}/stornieren/{loginname}")
    public String stornieren(@PathVariable long anzeigeid, @PathVariable String loginname, Model model){
        logger.info("GET /admin/anzeige/{}/bestellen/{}", anzeigeid, loginname);

        try {
            Anzeige anzeige = anzeigeService.findAnzeigeById(anzeigeid)
                    .orElseThrow(() -> new AnzeigeException("Anzeige mit ID " + anzeigeid + " nicht gefunden"));

            Benutzer benutzer = benutzerService.findBenutzerById(loginname)
                    .orElseThrow(() -> new AnzeigeException("Benutzer " + loginname + " nicht gefunden"));

            anzeigeService.stornieren(anzeige, benutzer);

        } catch (AnzeigeException e) {
            logger.error("Fehler beim Stornieren: {}", e.getMessage());
            model.addAttribute("info", e.getMessage());
            return "redirect:/admin/anzeige";
        }

        return "redirect:/admin/anzeige";
    }

    @GetMapping("/admin/anzeige/verlosung")
    public String verlosung() {
        logger.info("GET /admin/anzeige/verlosung");
        anzeigeService.verlosen();
        return "redirect:/admin/anzeige";
    }
    
}
