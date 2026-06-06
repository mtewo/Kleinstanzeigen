package de.hsrm.mi.web.webprojekt.anzeige.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.webprojekt.anzeige.ui.AnzeigeException;
import de.hsrm.mi.web.webprojekt.entities.anzeige.Anzeige;
import de.hsrm.mi.web.webprojekt.entities.anzeige.AnzeigeRepository;
import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.webprojekt.entities.benutzer.BenutzerRepository;

@Service
public class AnzeigeServiceImpl implements AnzeigeService {

    Logger logger = LoggerFactory.getLogger(AnzeigeServiceImpl.class);
 
    private final AnzeigeRepository anzeigeRepository;
    private final BenutzerRepository benutzerRepository;
 
    public AnzeigeServiceImpl(AnzeigeRepository anzeigeRepository, BenutzerRepository benutzerRepository) {
        this.anzeigeRepository = anzeigeRepository;
        this.benutzerRepository = benutzerRepository;
    }
 
    @Override
    public Anzeige saveAnzeige(Anzeige anzeige) {
        logger.info("saveAnzeige: {}", anzeige);
        try {
            return anzeigeRepository.save(anzeige);
        } catch (Exception e) {
            logger.error("Fehler beim Speichern: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
 
    @Override
    public Optional<Anzeige> findAnzeigeById(long id) {
        logger.info("findAnzeigeById: {}", id);
        return anzeigeRepository.findById(id);
    }
 
    @Override
    public Collection<Anzeige> findAllAnzeigen() {
        logger.info("findAllAnzeigen");
        return anzeigeRepository.findAll(Sort.by("ablaufdatum"));
    }
 
    @Override
    public void deleteAnzeigeById(long id) {
        logger.info("deleteAnzeigeById: {}", id);
        anzeigeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bestellen(Anzeige a, Benutzer b){

        if(a.getAnbieter().equals(b)) {
        logger.warn("Anbieter {} versucht eigene Anzeige {} zu bestellen", b.getLoginName(), a.getId());
        throw new AnzeigeException("Anbieter darf nicht bestellen");
        }
        if (a.getBesteller().size() >= a.getAnzahl()) {
            logger.warn("Anzeige {} ist ausverkauft", a.getId());
            throw new AnzeigeException("Keine Exemplare mehr verfügbar");
        }
        if (a.getBesteller().contains(b)) {
            logger.warn("Benutzer {} hat Anzeige {} bereits bestellt", b.getLoginName(), a.getId());
            throw new AnzeigeException("Benutzer hat bereits bestellt");
        }

        a.getBesteller().add(b);
        anzeigeRepository.save(a);
        logger.info("Benutzer {} hat Anzeige {} bestellt", b.getLoginName(), a.getId());

    }

    @Override
    @Transactional
    public void stornieren(Anzeige a, Benutzer b){
        if(!a.getBesteller().contains(b)){
            logger.warn("Benutzer {} hat Anziege {} gar nicht bestellt.", b.getLoginName(), a.getId());
            throw new AnzeigeException("Benutzer hat diese Anzeige nicht bestellt");
        }
        a.getBesteller().remove(b);
        anzeigeRepository.save(a); //nicht nötig bei @Transactional
        logger.info("Benutzer {} hat Bestellung von Anzeige {} storniert", b.getLoginName(), a.getId());
    }

    @Override
    @Transactional
    public void verlosen(){
        // Alle Benutzer und Anzeigen laden
        List<Benutzer> alleBenutzer = new ArrayList<>(benutzerRepository.findAll());
        List<Anzeige> alleAnzeigen = new ArrayList<>(anzeigeRepository.findAll());

        if (alleBenutzer.isEmpty() || alleAnzeigen.isEmpty()) {
            logger.warn("Keine Benutzer oder Anzeigen vorhanden");
            return;
        }

        Random random = new Random();

        // Jeder Anzeige zufällig einen Anbieter zuordnen
        for (Anzeige anzeige : alleAnzeigen) {
            Benutzer zufaelligerAnbieter = alleBenutzer.get(random.nextInt(alleBenutzer.size()));
            anzeige.setAnbieter(zufaelligerAnbieter);
            anzeigeRepository.save(anzeige);
            logger.info("Anzeige {} bekommt Anbieter {}", anzeige.getId(), zufaelligerAnbieter.getLoginName());
        }

        //  Jedem Benutzer maximal 3 mal versuchen eine Anzeige zuzuordnen
        for (Benutzer benutzer : alleBenutzer) {
            for (int versuch = 0; versuch < 3; versuch++) {
                Anzeige zufaelligeAnzeige = alleAnzeigen.get(random.nextInt(alleAnzeigen.size()));
                try {
                    bestellen(zufaelligeAnzeige, benutzer);
                    logger.info("Benutzer {} bestellt Anzeige {} (Versuch {})", 
                        benutzer.getLoginName(), zufaelligeAnzeige.getId(), versuch + 1);
                } catch (AnzeigeException e) {
                    logger.info("Versuch {} fehlgeschlagen für Benutzer {}: {}", 
                        versuch + 1, benutzer.getLoginName(), e.getMessage());
                    // Versuch läuft ins Leere, weitermachen
                }
            }
        }
    }
}
