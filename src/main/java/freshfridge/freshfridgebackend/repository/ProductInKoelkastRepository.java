package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductInKoelkastRepository extends JpaRepository<ProductInKoelkast, Integer> {

    List<ProductInKoelkast> findByKoelkast_KoelkastId(Integer koelkastId);

    List<ProductInKoelkast> findByHoudbaarheidsdatumBefore(LocalDate datum);

    List<ProductInKoelkast> findByKoelkast_Gebruiker_Gebruikernr(Integer gebruikernr);
}
