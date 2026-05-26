package de.hsrm.mi.web.webprojekt.entities.benutzer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, String> {
    // <Entity Typ, Primarykey Typ>
    
}
