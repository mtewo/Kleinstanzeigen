package de.hsrm.mi.web.webprojekt.benutzer.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BenutzerController {

    

    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    @GetMapping("/admin/benutzer/{loginname}")
    public String benutzerBearbeiten(@PathVariable String loginname, Model model) {
        logger.info("GET /admin/benutzer/{}", loginname);

        model.getAttribute(loginname);

        return "benutzer/bearbeiten";

    }
    
    

}
