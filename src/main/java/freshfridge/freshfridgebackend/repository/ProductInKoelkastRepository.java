package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductInKoelkastRepository extends JpaRepository<ProductInKoelkast, Integer> {

    List<ProductInKoelkast> findByKoelkast_KoelkastId(Integer koelkastId);

    List<ProductInKoelkast> findByHoudbaarheidsdatum(LocalDate houdbaarheidsdatum);

    List<ProductInKoelkast> findByKoelkast_Gebruiker_Gebruikernr(Integer gebruikernr);

    Optional<ProductInKoelkast>
    findByKoelkast_KoelkastIdAndProduct_ProductIdAndHoudbaarheidsdatum(
            Integer koelkastId,
            Integer productId,
            LocalDate houdbaarheidsdatum
    );
}
