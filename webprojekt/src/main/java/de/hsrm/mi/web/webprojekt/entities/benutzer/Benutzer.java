package de.hsrm.mi.web.webprojekt.entities.benutzer;

import de.hsrm.mi.web.webprojekt.validators.GutesPasswort;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Benutzer {
    @Id
    public String loginName;

    @NotBlank
    @Size(min=3,max=60, message="Größe muss zwischen 3 und 60 sein")
    private String name = "";

    @NotBlank
    @Email(message="muss eine korrekt formatierte E-Mail-Adresse sein" )
    private String eMail = "";

    @NotBlank
    private String adresse = "";
    @NotBlank
    private String rolle = "";
    @NotBlank
    @GutesPasswort (message="Das Passwort darf Zweiundvierzig oder 42 nicht enthälten")
    private String passwort = "";

    private boolean aktiviert = true;
    
    @Version
    private long version = 0;

    public Benutzer() {} // leerer Konstruktor für JPA !Pflicht!

    
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

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

    public long getVersion(){
        return version;
    }

    public void setVersion(long version){
        this.version = version;
    }


     @Override
    public String toString() {
        return "BenutzerFormular [name=" + name + ", eMail=" + eMail + ", adresse=" + adresse + ", rolle=" + rolle
                + ", passwort=" + passwort +  ", aktiviert="
                + aktiviert + "]";
    }


     @Override
     public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
        return result;
     }


     @Override
     public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Benutzer other = (Benutzer) obj;
        if (loginName == null) {
            if (other.loginName != null)
                return false;
        } else if (!loginName.equals(other.loginName))
            return false;
        return true;
     }

    

   
}
