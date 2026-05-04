import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BenutzerController {

    @GetMapping("/admin/benutzer/{loginname}")

    Logger logger = LoggerFactory.getLogger(BenutzerController.class);

    public String getMethodName(@PathVariable String loginname, Model model) {
        logger.info(GET/admin/benutzer/{}, loginname);

        model.getAttribute(loginname);

    }
    
    

}
