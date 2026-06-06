package de.hsrm.mi.web.webprojekt.anzeige.api;

import java.time.LocalDate;

public record AnzeigeDTO(long id, String titel, 
    String beschreibung, int preis, int anzahl, LocalDate ablaufdatum, int verfuegbar, 
    String anbieterName, String anbieterAdresse) {
    
}
