package freshfridge.freshfridgebackend.controller;
import freshfridge.freshfridgebackend.entity.Koelkast;
import freshfridge.freshfridgebackend.service.KoelkastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import freshfridge.freshfridgebackend.dto.ProductInKoelkastResponse;
import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.service.ProductInKoelkastService;

import java.util.List;

@RestController
@RequestMapping("/api/koelkasten")
public class KoelkastController {

    private final KoelkastService koelkastService;
    private final ProductInKoelkastService pikService;

    public KoelkastController(KoelkastService koelkastService, ProductInKoelkastService pikService) {
        this.koelkastService = koelkastService;
        this.pikService = pikService;
    }

    @PostMapping("/gebruiker/{gebruikernr}")
    public ResponseEntity<Koelkast> addKoelkast(@PathVariable Integer gebruikernr,
                                                @RequestBody Koelkast koelkast) {
        Koelkast saved = koelkastService.addKoelkast(koelkast, gebruikernr);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Koelkast> getKoelkast(@PathVariable Integer id) {
        return ResponseEntity.ok(koelkastService.getKoelkast(id));
    }

    @GetMapping("/gebruiker/{gebruikernr}")
    public ResponseEntity<List<Koelkast>> getVanGebruiker(@PathVariable Integer gebruikernr) {
        return ResponseEntity.ok(koelkastService.getKoelkastenVanGebruiker(gebruikernr));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Koelkast> updateKoelkast(@PathVariable Integer id,
                                                   @RequestBody Koelkast koelkast) {
        return ResponseEntity.ok(koelkastService.updateKoelkast(id, koelkast));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKoelkast(@PathVariable Integer id) {
        koelkastService.deleteKoelkast(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{koelkastId}/producten")
    public ResponseEntity<List<ProductInKoelkastResponse>> getProductenInKoelkast(@PathVariable Integer koelkastId) {
        List<ProductInKoelkast> list = pikService.getProductenInKoelkast(koelkastId);

        List<ProductInKoelkastResponse> resp = list.stream()
                .map(pik -> new ProductInKoelkastResponse(
                        pik.getPikId(),
                        pik.getHoudbaarheidsdatum(),
                        pik.getToegevoegdOp(),
                        pik.getProduct().getProductId(),
                        pik.getProduct().getBarcode(),
                        pik.getProduct().getNaam(),
                        pik.getProduct().getCategorie(),
                        pik.getProduct().getImageUrl(),
                        pik.getAantal() == null ? 1 : pik.getAantal()
                        ))
                .toList();

        return ResponseEntity.ok(resp);
    }

}
