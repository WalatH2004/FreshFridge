package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Gebruiker")
public class Gebruiker {

    @Id
    @Column(name = "gebruikernr")
    private Integer gebruikernr;

    @Column(name = "voornaam", nullable = false)
    private String voornaam;

    @Column(name = "achternaam", nullable = false)
    private String achternaam;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    public Integer getGebruikersnr() {
        return gebruikernr;
    }

    public void setGebruikersnr(Integer gebruikernr) {
        this.gebruikernr = gebruikernr;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
