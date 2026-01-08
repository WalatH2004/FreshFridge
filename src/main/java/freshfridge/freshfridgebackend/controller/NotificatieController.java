package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.service.NotificatieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaties")
public class NotificatieController {

    private final NotificatieService notificatieService;

    public NotificatieController(NotificatieService notificatieService) {
        this.notificatieService = notificatieService;
    }

    @PostMapping("/gebruiker/{gebruikernr}")
    public ResponseEntity<Notificatie> add(@PathVariable Integer gebruikernr,
                                           @RequestBody Notificatie notificatie) {
        Notificatie saved = notificatieService.addNotificatie(notificatie, gebruikernr);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/gebruiker/{gebruikernr}")
    public ResponseEntity<List<Notificatie>> getByGebruiker(@PathVariable Integer gebruikernr) {
        return ResponseEntity.ok(notificatieService.getNotificatiesVanGebruiker(gebruikernr));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificatie> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(notificatieService.getNotificatie(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        notificatieService.deleteNotificatie(id);
        return ResponseEntity.noContent().build();
    }
}
