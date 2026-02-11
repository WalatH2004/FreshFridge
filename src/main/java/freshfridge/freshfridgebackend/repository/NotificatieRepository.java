package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.entity.NotificatieType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificatieRepository extends JpaRepository<Notificatie, Integer> {

    List<Notificatie> findByGebruiker_GebruikernrOrderByNotificatieIdDesc(Integer gebruikernr);

    long countByGebruiker_GebruikernrAndGelezenFalse(Integer gebruikernr);

    boolean existsByGebruiker_GebruikernrAndTypeAndReferentiePikIdAndTriggerKey(
            Integer gebruikernr,
            NotificatieType type,
            Integer referentiePikId,
            String triggerKey
    );
}
