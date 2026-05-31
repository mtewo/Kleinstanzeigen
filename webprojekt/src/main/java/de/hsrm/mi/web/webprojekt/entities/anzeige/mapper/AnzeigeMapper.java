package de.hsrm.mi.web.webprojekt.entities.anzeige.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import de.hsrm.mi.web.webprojekt.anzeige.ui.AnzeigeFormular;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;

@Mapper(componentModel = "spring")
public interface AnzeigeMapper {
    AnzeigeFormular anzeigeToAnzeigeFormular(Anzeige anzeige);
 
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "anbieter", ignore = true)
    @Mapping(target = "besteller", ignore = true)
    Anzeige anzeigeFormularToAnzeige(AnzeigeFormular formular);
}
