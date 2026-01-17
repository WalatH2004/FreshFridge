package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Koelkast")
public class Koelkast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koelkastId")
    private Integer koelkastId;

    @Column(name = "naam", nullable = false)
    private String naam;

    @Column(name = "locatie")
    private String locatie;

    @ManyToOne
    @JoinColumn(name = "gebruikernr", nullable = false)
    private Gebruiker gebruiker;

    public Koelkast() {
    }

    public Integer getKoelkastId() {
        return koelkastId;
    }

    public void setKoelkastId(Integer koelkastId) {
        this.koelkastId = koelkastId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
}
