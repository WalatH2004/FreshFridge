package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.repository.GebruikerRepository;
import freshfridge.freshfridgebackend.repository.NotificatieRepository;
import freshfridge.freshfridgebackend.service.NotificatieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificatieServiceImpl implements NotificatieService {

    private final NotificatieRepository notificatieRepository;
    private final GebruikerRepository gebruikerRepository;

    public NotificatieServiceImpl(NotificatieRepository notificatieRepository,
                                  GebruikerRepository gebruikerRepository) {
        this.notificatieRepository = notificatieRepository;
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    public Notificatie addNotificatie(Notificatie notificatie, Integer gebruikernr) {
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikernr)
                .orElseThrow(() -> new EntityNotFoundException("Gebruiker niet gevonden"));

        if (notificatie.getTijd() == null || notificatie.getTijd().isBlank()) {
            String tijd = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
            notificatie.setTijd(tijd);
        }

        notificatie.setGebruiker(gebruiker);
        return notificatieRepository.save(notificatie);
    }

    @Override
    public Notificatie getNotificatie(Integer notificatieId) {
        return notificatieRepository.findById(notificatieId)
                .orElseThrow(() -> new EntityNotFoundException("Notificatie niet gevonden"));
    }

    @Override
    public List<Notificatie> getNotificatiesVanGebruiker(Integer gebruikernr) {
        return notificatieRepository.findByGebruiker_Gebruikernr(gebruikernr);
    }

    @Override
    public void deleteNotificatie(Integer notificatieId) {
        if (!notificatieRepository.existsById(notificatieId)) {
            throw new EntityNotFoundException("Notificatie niet gevonden");
        }
        notificatieRepository.deleteById(notificatieId);
    }
}
