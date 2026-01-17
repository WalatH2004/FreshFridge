package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.dto.ScanRequest;
import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.service.ProductInKoelkastService;
import freshfridge.freshfridgebackend.service.ProductOpzoekService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScanController {

    private final ProductOpzoekService productLookupService;
    private final ProductInKoelkastService pikService;

    public ScanController(ProductOpzoekService productLookupService,
                          ProductInKoelkastService pikService) {
        this.productLookupService = productLookupService;
        this.pikService = pikService;
    }

    @PostMapping("/scan")
    public ResponseEntity<ProductInKoelkast> scan(@RequestBody ScanRequest req) {
        if (req.getBarcode() == null || req.getBarcode().isBlank()
                || req.getKoelkastId() == null
                || req.getHoudbaarheidsdatum() == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productLookupService.getOrCreateByBarcode(req.getBarcode());

        ProductInKoelkast pik = new ProductInKoelkast();
        pik.setHoudbaarheidsdatum(req.getHoudbaarheidsdatum());

        ProductInKoelkast saved = pikService.addProductInKoelkast(
                pik,
                product.getProductId(),
                req.getKoelkastId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
