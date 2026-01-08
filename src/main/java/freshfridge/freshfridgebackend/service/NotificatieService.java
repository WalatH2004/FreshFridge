package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Notificatie;

import java.util.List;

public interface NotificatieService {

    Notificatie addNotificatie(Notificatie notificatie, Integer gebruikernr);

    Notificatie getNotificatie(Integer notificatieId);

    List<Notificatie> getNotificatiesVanGebruiker(Integer gebruikernr);

    void deleteNotificatie(Integer notificatieId);
}
