package de.hsrm.mi.web.webprojekt.anzeige.ui;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class AnzeigeFormular{
   @NotBlank
    @Size(min = 3, max = 40)
    private String titel = "";
 
    @NotBlank
    @Size(min = 17)
    private String beschreibung = "";
 
    @PositiveOrZero
    private int preis = 0;
 
    @PositiveOrZero
    private int anzahl = 0;
 
    @Future
    private LocalDate ablaufdatum = LocalDate.now().plusDays(30);
 
    private long version = 0;
 
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

 
    public long getVersion() { 
        return version; 
    }

    public void setVersion(long version) { 
        this.version = version; 
    }

 
    @Override
    public String toString() {
        return "AnzeigeFormular [titel=" + titel + ", preis=" + preis + ", anzahl=" + anzahl + ", ablaufdatum=" + ablaufdatum + "]";
    } 
}