package freshfridge.freshfridgebackend.service.impl;

import freshfridge.freshfridgebackend.entity.Koelkast;
import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.entity.Scanner;
import freshfridge.freshfridgebackend.repository.KoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductInKoelkastRepository;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import freshfridge.freshfridgebackend.repository.ScannerRepository;
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
    private final ScannerRepository scannerRepository;

    public ProductInKoelkastServiceImpl(ProductInKoelkastRepository pikRepository,
                                        ProductRepository productRepository,
                                        KoelkastRepository koelkastRepository,
                                        ScannerRepository scannerRepository) {
        this.pikRepository = pikRepository;
        this.productRepository = productRepository;
        this.koelkastRepository = koelkastRepository;
        this.scannerRepository = scannerRepository;
    }

    @Override
    public ProductInKoelkast addProductInKoelkast(ProductInKoelkast pik,
                                                  Integer productId,
                                                  Integer koelkastId,
                                                  Integer scannerId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product niet gevonden"));

        Koelkast koelkast = koelkastRepository.findById(koelkastId)
                .orElseThrow(() -> new EntityNotFoundException("Koelkast niet gevonden"));

        Scanner scanner = scannerRepository.findById(scannerId)
                .orElseThrow(() -> new EntityNotFoundException("Scanner niet gevonden"));

        // Defaults als client ze niet stuurt
        if (pik.getToegevoegdOp() == null) {
            pik.setToegevoegdOp(LocalDate.now());
        }

        pik.setProduct(product);
        pik.setKoelkast(koelkast);
        pik.setScanner(scanner);

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
