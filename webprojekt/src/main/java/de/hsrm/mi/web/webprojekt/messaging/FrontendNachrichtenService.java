package de.hsrm.mi.web.webprojekt.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;



@Service
public class FrontendNachrichtenService {

    Logger logger = LoggerFactory.getLogger(FrontendNachrichtenService.class);

    private final SimpMessagingTemplate messagingTemplate;

    public FrontendNachrichtenService(SimpMessagingTemplate messagingTemplate) { 
        this.messagingTemplate = messagingTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendEvent(FrontendNachrichtEvent ev) {
        logger.info("Sende Event an Frontend: {}", ev);
        messagingTemplate.convertAndSend("/topic/anzeige", ev);
    }

    // Trqnsaktion commited erst nach return, Normaler @Eventlistner Nachricht soforrt beim publishEvent(..) also alte Datenbankzustand
    //phase = TransactionPhase.AFTER_COMMIT Nachricht erst nach commit (return) senden
}
