package de.hsrm.mi.web.webprojekt.benutzer.services;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.webprojekt.entities.benutzer.BenutzerRepository;
@Service
public class BenutzerServiceImpl implements BenutzerService{

    Logger logger = LoggerFactory.getLogger(BenutzerServiceImpl.class);

    private final BenutzerRepository benutzerRepository;

    // Constructor Injection 
    public BenutzerServiceImpl(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }
    @Override
    public Benutzer saveBenutzer(Benutzer b) {
        logger.info("saveBenutzer: {}", b);
        try {
            return benutzerRepository.save(b);
        } catch (Exception e) {
            logger.error("Fehler beim Speichern: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        
    }

    @Override
    public Optional<Benutzer> findBenutzerById(String loginName) {
       logger.info("findBenutzerById: {}", loginName);
        return benutzerRepository.findById(loginName);
    }

    @Override
    public Collection<Benutzer> findAllBenutzer() {
        logger.info("findAllBenutzer");
        return benutzerRepository.findAll(Sort.by(Sort.Direction.ASC, "loginName"));
    }

    @Override
    public void deleteBenutzerById(String loginName) {
        logger.info("deleteBenutzerById: {}", loginName);
        benutzerRepository.deleteById(loginName);
    }
}
