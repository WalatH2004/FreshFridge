package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.entity.NotificatieType;
import freshfridge.freshfridgebackend.repository.NotificatieRepository;
import freshfridge.freshfridgebackend.service.NotificatieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificatieServiceImpl implements NotificatieService {

    private final NotificatieRepository notificatieRepository;

    public NotificatieServiceImpl(NotificatieRepository notificatieRepository) {
        this.notificatieRepository = notificatieRepository;
    }

    @Override
    public Notificatie create(Gebruiker gebruiker, NotificatieType type, String bericht, Integer referentiePikId, String triggerKey) {

        Integer gebruikernr = gebruiker.getGebruikernr();

        // dedupe: als dezelfde trigger al bestaat, maak niets aan
        if (triggerKey != null && referentiePikId != null) {
            boolean exists = notificatieRepository.existsByGebruiker_GebruikernrAndTypeAndReferentiePikIdAndTriggerKey(
                    gebruikernr, type, referentiePikId, triggerKey
            );
            if (exists) return null;
        }

        Notificatie n = new Notificatie();
        n.setGebruiker(gebruiker);
        n.setType(type);
        n.setBericht(bericht);
        n.setDatum(LocalDate.now());
        n.setTijd(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        n.setGelezen(false);
        n.setReferentiePikId(referentiePikId);
        n.setTriggerKey(triggerKey);

        return notificatieRepository.save(n);
    }

    @Override
    public List<Notificatie> getForUser(Integer gebruikernr) {
        return notificatieRepository.findByGebruiker_GebruikernrOrderByNotificatieIdDesc(gebruikernr);
    }

    @Override
    public long countUnread(Integer gebruikernr) {
        return notificatieRepository.countByGebruiker_GebruikernrAndGelezenFalse(gebruikernr);
    }

    @Override
    public void markAsRead(Integer notificatieId) {
        Notificatie n = notificatieRepository.findById(notificatieId)
                .orElseThrow(() -> new EntityNotFoundException("Notificatie niet gevonden"));
        n.setGelezen(true);
        notificatieRepository.save(n);
    }
}