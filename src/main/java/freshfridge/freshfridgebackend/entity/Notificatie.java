package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Notificatie")
public class Notificatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificatieId")
    private Integer notificatieId;

    @Column(name = "bericht", length = 250)
    private String bericht;

    @Column(name = "tijd", nullable = false, length = 5)
    private String tijd;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @ManyToOne
    @JoinColumn(name = "gebruikernr", nullable = false)
    private Gebruiker gebruiker;

    public Notificatie() {}

    public Integer getNotificatieId() { return notificatieId; }
    public void setNotificatieId(Integer notificatieId) { this.notificatieId = notificatieId; }

    public String getBericht() { return bericht; }
    public void setBericht(String bericht) { this.bericht = bericht; }

    public String getTijd() { return tijd; }
    public void setTijd(String tijd) { this.tijd = tijd; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Gebruiker getGebruiker() { return gebruiker; }
    public void setGebruiker(Gebruiker gebruiker) { this.gebruiker = gebruiker; }
}
