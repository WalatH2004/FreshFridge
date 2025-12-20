package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Gebruiker;

public interface GebruikerService {

    Gebruiker createGebruiker(Gebruiker gebruiker);

    Gebruiker getGebruiker(Integer gebruikernr);
}
