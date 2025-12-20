package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.service.ProductInKoelkastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producten-in-koelkast")
public class ProductInKoelkastController {

    private final ProductInKoelkastService pikService;

    public ProductInKoelkastController(ProductInKoelkastService pikService) {
        this.pikService = pikService;
    }

    @PostMapping
    public ResponseEntity<ProductInKoelkast> add(@RequestParam Integer productId,
                                                 @RequestParam Integer koelkastId,
                                                 @RequestParam Integer scannerId,
                                                 @RequestBody ProductInKoelkast pik) {
        ProductInKoelkast saved = pikService.addProductInKoelkast(pik, productId, koelkastId, scannerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{pikId}")
    public ResponseEntity<ProductInKoelkast> getOne(@PathVariable Integer pikId) {
        return ResponseEntity.ok(pikService.getProductInKoelkast(pikId));
    }

    @GetMapping("/koelkast/{koelkastId}")
    public ResponseEntity<List<ProductInKoelkast>> getByKoelkast(@PathVariable Integer koelkastId) {
        return ResponseEntity.ok(pikService.getProductenInKoelkast(koelkastId));
    }

    @GetMapping("/gebruiker/{gebruikernr}")
    public ResponseEntity<List<ProductInKoelkast>> getByGebruiker(@PathVariable Integer gebruikernr) {
        return ResponseEntity.ok(pikService.getProductenVanGebruiker(gebruikernr));
    }

    @DeleteMapping("/{pikId}")
    public ResponseEntity<Void> delete(@PathVariable Integer pikId) {
        pikService.deleteProductInKoelkast(pikId);
        return ResponseEntity.noContent().build();
    }
}
