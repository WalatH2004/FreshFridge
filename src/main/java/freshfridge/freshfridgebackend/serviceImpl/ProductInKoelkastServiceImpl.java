package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.*;
import freshfridge.freshfridgebackend.repository.KoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductInKoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import freshfridge.freshfridgebackend.service.ProductInKoelkastService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import freshfridge.freshfridgebackend.service.NotificatieService;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductInKoelkastServiceImpl implements ProductInKoelkastService {

    private final ProductInKoelkastRepository pikRepository;
    private final ProductRepository productRepository;
    private final KoelkastRepository koelkastRepository;
    private final NotificatieService notificatieService;

    public ProductInKoelkastServiceImpl(ProductInKoelkastRepository pikRepository,
                                        ProductRepository productRepository,
                                        KoelkastRepository koelkastRepository,
                                        NotificatieService notificatieService) {
        this.pikRepository = pikRepository;
        this.productRepository = productRepository;
        this.koelkastRepository = koelkastRepository;
        this.notificatieService = notificatieService;
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
        if (pik.getAantal() == null || pik.getAantal() < 1) {
            pik.setAantal(1);
        }

        var existingOpt = pikRepository
                .findByKoelkast_KoelkastIdAndProduct_ProductIdAndHoudbaarheidsdatum(
                        koelkastId, productId, pik.getHoudbaarheidsdatum()
                );

        if (existingOpt.isPresent()) {
            ProductInKoelkast existing = existingOpt.get();
            existing.setAantal(existing.getAantal() + pik.getAantal());

            ProductInKoelkast saved = pikRepository.save(existing);

            createToegevoegdNotificatie(koelkast, product, saved);

            return saved;
        }

        pik.setProduct(product);
        pik.setKoelkast(koelkast);

        ProductInKoelkast saved = pikRepository.save(pik);

        createToegevoegdNotificatie(koelkast, product, saved);

        return saved;
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

    @Override
    public void changeAantal(Integer pikId, int delta) {
        ProductInKoelkast pik = pikRepository.findById(pikId)
                .orElseThrow(() -> new EntityNotFoundException("ProductInKoelkast niet gevonden"));

        int current = (pik.getAantal() == null) ? 1 : pik.getAantal();
        int next = current + delta;

        if (next <= 0) {
            pikRepository.deleteById(pikId);
            return;
        }

        pik.setAantal(next);
        pikRepository.save(pik);
    }

    private void createToegevoegdNotificatie(Koelkast koelkast, Product product, ProductInKoelkast pik) {
        String bericht =
                "Toegevoegd: " + product.getNaam() +
                        " (" + (pik.getAantal() == null ? 1 : pik.getAantal()) + "x) in " + koelkast.getNaam() +
                        " – houdbaar t/m " + pik.getHoudbaarheidsdatum();

        // triggerKey: kies iets stabiels; referentiePikId = pikId
        // LET OP: door dedupe wordt dezelfde ADD voor dezelfde pikId maar 1x gemaakt
        notificatieService.create(
                koelkast.getGebruiker(),
                NotificatieType.PRODUCT_TOEGEVOEGD,
                bericht,
                pik.getPikId(),
                "ADD"
        );
    }
}
