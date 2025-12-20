package freshfridge.freshfridgebackend.serviceImpl;
import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.entity.Koelkast;
import freshfridge.freshfridgebackend.repository.GebruikerRepository;
import freshfridge.freshfridgebackend.repository.KoelkastRepository;
import freshfridge.freshfridgebackend.service.KoelkastService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoelkastServiceImpl implements KoelkastService {

    private final KoelkastRepository koelkastRepository;
    private final GebruikerRepository gebruikerRepository;

    public KoelkastServiceImpl(KoelkastRepository koelkastRepository,
                               GebruikerRepository gebruikerRepository) {
        this.koelkastRepository = koelkastRepository;
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    public Koelkast addKoelkast(Koelkast koelkast, Integer gebruikernr) {
        Gebruiker gebruiker = gebruikerRepository.findById(gebruikernr)
                .orElseThrow(() -> new EntityNotFoundException("Gebruiker niet gevonden"));

        koelkast.setGebruiker(gebruiker);
        return koelkastRepository.save(koelkast);
    }

    @Override
    public Koelkast getKoelkast(Integer id) {
        return koelkastRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Koelkast niet gevonden"));
    }

    @Override
    public List<Koelkast> getKoelkastenVanGebruiker(Integer gebruikernr) {
        return koelkastRepository.findByGebruiker_Gebruikernr(gebruikernr);
    }

    @Override
    public Koelkast updateKoelkast(Integer id, Koelkast koelkast) {
        Koelkast bestaand = koelkastRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Koelkast niet gevonden"));

        bestaand.setNaam(koelkast.getNaam());
        bestaand.setLocatie(koelkast.getLocatie());

        return koelkastRepository.save(bestaand);
    }

    @Override
    public void deleteKoelkast(Integer id) {
        if (!koelkastRepository.existsById(id)) {
            throw new EntityNotFoundException("Koelkast niet gevonden");
        }
        koelkastRepository.deleteById(id);
    }
}
