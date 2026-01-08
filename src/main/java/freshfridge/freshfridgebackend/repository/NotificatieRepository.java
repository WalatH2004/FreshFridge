package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.Notificatie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificatieRepository extends JpaRepository<Notificatie, Integer> {
    List<Notificatie> findByGebruiker_Gebruikernr(Integer gebruikernr);
}
