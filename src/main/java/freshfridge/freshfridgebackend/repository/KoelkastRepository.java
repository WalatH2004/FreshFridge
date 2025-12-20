package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.Koelkast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoelkastRepository extends JpaRepository<Koelkast, Integer> {

    List<Koelkast> findByGebruiker_Gebruikernr(Integer gebruikernr);
}
