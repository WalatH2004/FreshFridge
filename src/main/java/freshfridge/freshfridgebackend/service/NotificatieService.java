package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Notificatie;
import freshfridge.freshfridgebackend.entity.NotificatieType;
import freshfridge.freshfridgebackend.entity.Gebruiker;

import java.util.List;

public interface NotificatieService {
    Notificatie create(Gebruiker gebruiker, NotificatieType type, String bericht, Integer referentiePikId, String triggerKey);
    List<Notificatie> getForUser(Integer gebruikernr);
    long countUnread(Integer gebruikernr);
    void markAsRead(Integer notificatieId);
}