package freshfridge.freshfridgebackend.controller;
import freshfridge.freshfridgebackend.entity.Koelkast;
import freshfridge.freshfridgebackend.service.KoelkastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/koelkasten")
public class KoelkastController {

    private final KoelkastService koelkastService;

    public KoelkastController(KoelkastService koelkastService) {
        this.koelkastService = koelkastService;
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
}
