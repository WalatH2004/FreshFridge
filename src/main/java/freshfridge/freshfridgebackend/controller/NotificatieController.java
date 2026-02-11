package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.service.NotificatieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaties")
public class NotificatieController {

    private final NotificatieService notificatieService;

    public NotificatieController(NotificatieService notificatieService) {
        this.notificatieService = notificatieService;
    }

    // Consistent met andere controllers (bv. /api/producten-in-koelkast/gebruiker/{gebruikernr})
    @GetMapping("/gebruiker/{gebruikernr}")
    public List<Notificatie> getForUser(@PathVariable Integer gebruikernr) {
        return notificatieService.getForUser(gebruikernr);
    }

    @GetMapping("/gebruiker/{gebruikernr}/unread-count")
    public long unreadCount(@PathVariable Integer gebruikernr) {
        return notificatieService.countUnread(gebruikernr);
    }

    // Frontend gebruikt PUT; laat ook POST toe voor backwards compatibility.
    @PutMapping("/{notificatieId}/read")
    public void markAsReadPut(@PathVariable Integer notificatieId) {
        notificatieService.markAsRead(notificatieId);
    }

    @PostMapping("/{notificatieId}/read")
    public void markAsReadPost(@PathVariable Integer notificatieId) {
        notificatieService.markAsRead(notificatieId);
    }
}