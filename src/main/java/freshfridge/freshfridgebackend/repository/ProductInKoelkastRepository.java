package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductInKoelkastRepository extends JpaRepository<ProductInKoelkast, Integer> {

    // Alle producten in één koelkast
    List<ProductInKoelkast> findByKoelkast_KoelkastId(Integer koelkastId);

    // Producten die verlopen zijn
    List<ProductInKoelkast> findByHoudbaarheidsdatumBefore(LocalDate datum);

    // Producten van een gebruiker (via koelkast)
    List<ProductInKoelkast> findByKoelkast_Gebruiker_Gebruikernr(Integer gebruikernr);
}
