package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Koelkast;
import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.repository.KoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductInKoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import freshfridge.freshfridgebackend.service.ProductInKoelkastService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductInKoelkastServiceImpl implements ProductInKoelkastService {

    private final ProductInKoelkastRepository pikRepository;
    private final ProductRepository productRepository;
    private final KoelkastRepository koelkastRepository;

    public ProductInKoelkastServiceImpl(ProductInKoelkastRepository pikRepository,
                                        ProductRepository productRepository,
                                        KoelkastRepository koelkastRepository) {
        this.pikRepository = pikRepository;
        this.productRepository = productRepository;
        this.koelkastRepository = koelkastRepository;
    }

    @Override
    public ProductInKoelkast addProductInKoelkast(ProductInKoelkast pik,
                                                  Integer productId,
                                                  Integer koelkastId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product niet gevonden"));

        Koelkast koelkast = koelkastRepository.findById(koelkastId)
                .orElseThrow(() -> new EntityNotFoundException("Koelkast niet gevonden"));

        if (pik.getToegevoegdOp() == null) {
            pik.setToegevoegdOp(LocalDate.now());
        }

        pik.setProduct(product);
        pik.setKoelkast(koelkast);

        return pikRepository.save(pik);
    }

    @Override
    public ProductInKoelkast getProductInKoelkast(Integer pikId) {
        return pikRepository.findById(pikId)
                .orElseThrow(() -> new EntityNotFoundException("ProductInKoelkast niet gevonden"));
    }

    @Override
    public List<ProductInKoelkast> getProductenInKoelkast(Integer koelkastId) {
        return pikRepository.findByKoelkast_KoelkastId(koelkastId);
    }

    @Override
    public List<ProductInKoelkast> getProductenVanGebruiker(Integer gebruikernr) {
        return pikRepository.findByKoelkast_Gebruiker_Gebruikernr(gebruikernr);
    }

    @Override
    public void deleteProductInKoelkast(Integer pikId) {
        if (!pikRepository.existsById(pikId)) {
            throw new EntityNotFoundException("ProductInKoelkast niet gevonden");
        }
        pikRepository.deleteById(pikId);
    }
}
