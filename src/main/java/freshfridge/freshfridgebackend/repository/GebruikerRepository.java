package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {

    Optional<Gebruiker> findByEmail(String email);
}
