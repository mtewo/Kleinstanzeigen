package de.hsrm.mi.web.webprojekt.entities.benutzer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
//import org.springframework.web.bind.annotation.Mapping;

import de.hsrm.mi.web.webprojekt.benutzer.ui.BenutzerFormular;
import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;

@Mapper(componentModel = "spring")
public interface BenutzerMapper {
    /*public BenutzerFormular benutzerToBenutzerFormular(Benutzer benutzer){
        BenutzerFormular formular = new BenutzerFormular();
        formular.setName(benutzer.getName());
        formular.seteMail(benutzer.geteMail());
        formular.setAdresse(benutzer.getAdresse());
        formular.setRolle(benutzer.getRolle());
        formular.setAktiviert(benutzer.isAktiviert());
        // passwort wird NICHT übertragen - Sicherheit!
        // loginName hat kein Feld im Formular
        return formular;
    }
    public Benutzer benutzerFormularToBenutzer (BenutzerFormular formular){
        Benutzer benutzer = new Benutzer();
        benutzer.setName(formular.getName());
        benutzer.seteMail(formular.geteMail());
        benutzer.setAdresse(formular.getAdresse());
        benutzer.setRolle(formular.getRolle());
        benutzer.setAktiviert(formular.isAktiviert());
        benutzer.setPasswort(formular.getPasswort());
        // passwortWiederholung wird NICHT übertragen
        // loginName kommt aus PathVariable, nicht aus Formular
        return benutzer;
    }*/
    @Mapping(target = "eMail", source = "eMail")
    @Mapping(target = "passwort", ignore = true)
    BenutzerFormular benutzerToBenutzerFormular(Benutzer benutzer);

    @Mapping(target = "loginName", ignore = true)
    Benutzer benutzerFormularToBenutzer(BenutzerFormular formular);

}
