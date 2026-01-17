package freshfridge.freshfridgebackend.serviceImpl;
import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.repository.GebruikerRepository;
import freshfridge.freshfridgebackend.service.GebruikerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GebruikerServiceImpl implements GebruikerService {

    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;

    public GebruikerServiceImpl(GebruikerRepository gebruikerRepository,
                                PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Gebruiker> getAllGebruikers() {
        return gebruikerRepository.findAll();
    }

    @Override
    public Gebruiker createGebruiker(Gebruiker gebruiker) {
        gebruiker.setPassword(passwordEncoder.encode(gebruiker.getPassword()));
        return gebruikerRepository.save(gebruiker);
    }

    @Override
    public Gebruiker getGebruiker(Integer gebruikernr) {
        return gebruikerRepository.findById(gebruikernr)
                .orElseThrow(() -> new EntityNotFoundException("Gebruiker niet gevonden"));
    }
}
