package freshfridge.freshfridgebackend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Notificatie")
public class Notificatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificatieId")
    private Integer notificatieId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private NotificatieType type;

    @Column(name = "bericht", length = 250)
    private String bericht;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "tijd", nullable = false, length = 5)
    private String tijd;

    @Column(name = "gelezen", nullable = false)
    private boolean gelezen = false;

    @Column(name = "referentiePikId")
    private Integer referentiePikId;

    @Column(name = "triggerKey", length = 40)
    private String triggerKey;

    @ManyToOne
    @JoinColumn(name = "gebruikernr", nullable = false)
    private Gebruiker gebruiker;

    public Notificatie() {}

    public Integer getNotificatieId() { return notificatieId; }
    public void setNotificatieId(Integer notificatieId) { this.notificatieId = notificatieId; }

    public NotificatieType getType() { return type; }
    public void setType(NotificatieType type) { this.type = type; }

    public String getBericht() { return bericht; }
    public void setBericht(String bericht) { this.bericht = bericht; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public String getTijd() { return tijd; }
    public void setTijd(String tijd) { this.tijd = tijd; }

    public boolean isGelezen() { return gelezen; }
    public void setGelezen(boolean gelezen) { this.gelezen = gelezen; }

    public Integer getReferentiePikId() { return referentiePikId; }
    public void setReferentiePikId(Integer referentiePikId) { this.referentiePikId = referentiePikId; }

    public String getTriggerKey() { return triggerKey; }
    public void setTriggerKey(String triggerKey) { this.triggerKey = triggerKey; }

    public Gebruiker getGebruiker() { return gebruiker; }
    public void setGebruiker(Gebruiker gebruiker) { this.gebruiker = gebruiker; }
}