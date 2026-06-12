package de.hsrm.mi.web.webprojekt.geo;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.service.registry.ImportHttpServices;

@Service
@ImportHttpServices
public class GeoServiceImpl implements GeoService{

    Logger logger = LoggerFactory.getLogger(GeoServiceImpl.class);
    private final RestClient restClient;

    public GeoServiceImpl(RestClient.Builder builder){
        this.restClient = builder
            .baseUrl("https://nominatim.openstreetmap.org")
            .defaultHeader("User-Agent", "hsrm-webprojekt/1.0")
            .build();
    }

    @Override
    public List<GeoAdresse> findeAdressen(String such){
        
        if(such.isEmpty()){
            logger.error("Es wurde keine Adresse eingegeben");
            return List.of();
        }

        logger.info("findeAdressen: {}", such);

        List<GeoAdresse> kandidaten = restClient.get()
            .uri("/search?format=json&countrycodes=de&q={q}", such)
            .retrieve()
            .body(new ParameterizedTypeReference<List<GeoAdresse>>(){});

        logger.info("Treffer von Nominatim: {}", kandidaten.size());

        List<String> ausfiltern= List.of("country", "state", "region", "postcode");

        return kandidaten.stream().filter(a -> !ausfiltern.contains(a.addresstype())).collect(Collectors.toList());


    }
    
}
