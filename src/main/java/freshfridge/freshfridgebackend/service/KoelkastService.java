package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Koelkast;
import java.util.List;

public interface KoelkastService {

    Koelkast addKoelkast(Koelkast koelkast, Integer gebruikernr);

    Koelkast getKoelkast(Integer id);

    List<Koelkast> getKoelkastenVanGebruiker(Integer gebruikernr);

    Koelkast updateKoelkast(Integer id, Koelkast koelkast);

    void deleteKoelkast(Integer id);
}
