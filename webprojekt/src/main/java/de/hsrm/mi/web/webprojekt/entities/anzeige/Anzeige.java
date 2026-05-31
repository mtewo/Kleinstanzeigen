package de.hsrm.mi.web.webprojekt.entities.anzeige;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.webprojekt.entities.benutzer.Benutzer;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Entity
public class Anzeige {

    @Id @GeneratedValue
    private long id;

    @Version
    private long version = 0; 

    @NotBlank 
    @Size(min=3, max=40 )
    private String titel = "";

    @NotBlank
    @Size(min=17)
    private String beschreibung = "";

    @PositiveOrZero
    private int preis = 0;

    @PositiveOrZero
    private int anzahl = 0;

    @Future
    private LocalDate ablaufdatum;

    @ManyToOne (optional=true)
    private Benutzer anbieter = null;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Benutzer> besteller = new ArrayList<>();

    public Anzeige(){}

    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public LocalDate getAblaufdatum() {
        return ablaufdatum;
    }

    public void setAblaufdatum(LocalDate ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        Anzeige other = (Anzeige) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Anzeige [id=" + id + ", version=" + version + ", titel=" + titel + ", beschreibung=" + beschreibung
                + ", preis=" + preis + ", anzahl=" + anzahl + ", ablaufdatum=" + ablaufdatum + "]";
    }

    public Benutzer getAnbieter() {
        return anbieter;
    }

    public void setAnbieter(Benutzer anbieter) {
        this.anbieter = anbieter;
    }

    public List<Benutzer> getBesteller() {
        return besteller;
    }

    public void setBesteller(List<Benutzer> bestller) {
        this.besteller = bestller;
    }

    





    
}
