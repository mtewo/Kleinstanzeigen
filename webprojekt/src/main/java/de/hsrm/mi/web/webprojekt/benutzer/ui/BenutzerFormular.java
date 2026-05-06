package de.hsrm.mi.web.webprojekt.benutzer.ui;


public class BenutzerFormular {
    
    private String name = "";
    private String eMail = "";
    private String adresse = "";
    private String rolle = "";
    private String passwort = "";
    private String passwortWiederholung = "";
    private boolean aktiviert = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public boolean isAktiviert() {
        return aktiviert;
    }

    public void setAktiviert(boolean aktieviert) {
        this.aktiviert = aktieviert;
    }

    public String getPasswortWiederholung() {
        return passwortWiederholung;
    }

    public void setPasswortWiederholung(String passwortWiederholung) {
        this.passwortWiederholung = passwortWiederholung;
    }

     @Override
    public String toString() {
        return "BenutzerFormular [name=" + name + ", eMail=" + eMail + ", adresse=" + adresse + ", rolle=" + rolle
                + ", passwort=" + passwort + ", passwortWiederholung=" + passwortWiederholung + ", aktiviert="
                + aktiviert + "]";
    }



}
