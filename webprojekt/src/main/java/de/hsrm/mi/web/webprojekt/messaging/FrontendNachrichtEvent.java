package de.hsrm.mi.web.webprojekt.messaging;

//import de.hsrm.mi.web.webprojekt.messaging.FrontendNachrichtEvent.NachrichtenTyp;

public class FrontendNachrichtEvent {

    public enum NachrichtenTyp { ANZEIGE }
    public enum Operation {CREATE, UPDATE, DELETE}

    private final NachrichtenTyp typ;
    private final long id;
    private final Operation operation;


        public FrontendNachrichtEvent(NachrichtenTyp typ, long id, Operation operation) {
        this.typ = typ;
        this.id = id;
        this.operation = operation;
    }

    public NachrichtenTyp getTyp() {
        return typ;
    }

    public long getId() {
        return id;
    }

    public Operation getOperation() {
        return operation;
    }

    
}
