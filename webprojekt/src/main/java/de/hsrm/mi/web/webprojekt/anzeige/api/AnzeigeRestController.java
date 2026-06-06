package de.hsrm.mi.web.webprojekt.anzeige.api;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.webprojekt.anzeige.services.AnzeigeService;
import de.hsrm.mi.web.webprojekt.anzeige.ui.AnzeigeNotFoundException;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;
import de.hsrm.mi.web.webprojekt.entities.anzeige.mapper.AnzeigeMapper;


@RestController
public class AnzeigeRestController {
    private final AnzeigeService anzeigeService;
    private final AnzeigeMapper anzeigeMapper;

    public AnzeigeRestController(AnzeigeService anzeigeService, AnzeigeMapper anzeigeMapper){
        this.anzeigeService = anzeigeService;
        this.anzeigeMapper = anzeigeMapper;
    }
    

    @GetMapping("/api/anzeige/{id}")
    public AnzeigeDTO anzeigeAlsJson(@PathVariable long id) {

        Optional<Anzeige> ifAnzeige = anzeigeService.findAnzeigeById(id);
        Optional<AnzeigeDTO> result;

        if(ifAnzeige.isPresent()){
            Anzeige anzeige = ifAnzeige.get();
            result = Optional.of(anzeigeMapper.anzeigeToAnzeigeDTO(anzeige));
        }else{
            result = Optional.empty();
        }


        return result.orElseThrow(() -> new AnzeigeNotFoundException(id));
    }

    @GetMapping("/api/anzeige")
    public List<AnzeigeDTO>alleAnzeigenAlsJson(){
        Collection<Anzeige> coll = anzeigeService.findAllAnzeigen();

        List<Anzeige> anzeigenList = new ArrayList<>(coll); 

        List<AnzeigeDTO> anzeigenListJson = anzeigeMapper.fromListAnzeigeTDO(anzeigenList);

        if (anzeigenListJson.isEmpty()){
            throw new AnzeigeNotFoundException();
        } else{
            return  anzeigenListJson;
        }
        
    }
    
}
