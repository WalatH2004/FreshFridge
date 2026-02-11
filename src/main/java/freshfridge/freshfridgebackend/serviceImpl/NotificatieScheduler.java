package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.NotificatieType;
import freshfridge.freshfridgebackend.entity.ProductInKoelkast;
import freshfridge.freshfridgebackend.repository.ProductInKoelkastRepository;
import freshfridge.freshfridgebackend.service.NotificatieService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class NotificatieScheduler {

    private final ProductInKoelkastRepository pikRepository;
    private final NotificatieService notificatieService;

    public NotificatieScheduler(ProductInKoelkastRepository pikRepository,
                                NotificatieService notificatieService) {
        this.pikRepository = pikRepository;
        this.notificatieService = notificatieService;
    }

    // Elke dag om 09:00
    @Scheduled(cron = "0 0 9 * * *")
    public void checkHoudbaarheid() {
        LocalDate today = LocalDate.now();

        notifyForDate(today.plusDays(3), NotificatieType.HOUDBAARHEID_GEEL, "GEEL");
        notifyForDate(today.plusDays(1), NotificatieType.HOUDBAARHEID_ORANJE, "ORANJE");
        notifyForDate(today.minusDays(1), NotificatieType.HOUDBAARHEID_ROOD, "ROOD");
    }

    private void notifyForDate(LocalDate targetDate,
                               NotificatieType type,
                               String triggerPrefix) {

        List<ProductInKoelkast> items =
                pikRepository.findByHoudbaarheidsdatum(targetDate);

        for (ProductInKoelkast pik : items) {

            String bericht =
                    "Let op: " + pik.getProduct().getNaam() +
                            " in " + pik.getKoelkast().getNaam() +
                            " (" + pik.getAantal() + "x) – houdbaar t/m " +
                            pik.getHoudbaarheidsdatum();

            // dedupe: 1 notificatie per pikId per dag per status
            String triggerKey = triggerPrefix + "_" + targetDate;

            notificatieService.create(
                    pik.getKoelkast().getGebruiker(),
                    type,
                    bericht,
                    pik.getPikId(),
                    triggerKey
            );
        }
    }
}