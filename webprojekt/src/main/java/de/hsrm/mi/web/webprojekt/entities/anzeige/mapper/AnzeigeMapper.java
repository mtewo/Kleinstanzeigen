package de.hsrm.mi.web.webprojekt.entities.anzeige.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import de.hsrm.mi.web.webprojekt.anzeige.api.AnzeigeDTO;
import de.hsrm.mi.web.webprojekt.anzeige.ui.AnzeigeFormular;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;

@Mapper(componentModel = "spring")
public interface AnzeigeMapper {
    AnzeigeFormular anzeigeToAnzeigeFormular(Anzeige anzeige);
 
    @Mapping(target = "id", ignore = true) //ignore -> felder "" in Anzeige leer lassen
    @Mapping(target = "anbieter", ignore = true)
    @Mapping(target = "besteller", ignore = true)
    Anzeige anzeigeFormularToAnzeige(AnzeigeFormular formular);

    
    @Mapping(target = "verfuegbar", expression="java( anzeige.getAnzahl() - anzeige.getBesteller().size() )")
    @Mapping(target = "anbieterName", source = "anbieter.name")
    @Mapping(target = "anbieterAdresse", source = "anbieter.adresse")
    AnzeigeDTO anzeigeToAnzeigeDTO(Anzeige anzeige);

    @Mapping(target = "anbieter", ignore = true)
    @Mapping(target = "besteller", ignore = true)
    @Mapping(target="version", ignore = true)
    Anzeige anzeigeDTOToAnzeige(AnzeigeDTO anzeigeDTO);
    // MapStruct generiert Implementierung(foreach, add) intern, da Einzelmethode (anzeigeToAnzeigeDTO) schon bekannt
    List<AnzeigeDTO> fromListAnzeigeTDO(List<Anzeige> anzeigen);
}
