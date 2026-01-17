package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Gebruiker;

import java.util.List;

public interface GebruikerService {

    Gebruiker createGebruiker(Gebruiker gebruiker);


    List<Gebruiker> getAllGebruikers();

    Gebruiker getGebruiker(Integer gebruikernr);
}
