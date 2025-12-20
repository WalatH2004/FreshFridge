package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.service.GebruikerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gebruiker")
public class GebruikerController {

    private final GebruikerService gebruikerService;

    public GebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gebruiker> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(gebruikerService.getGebruiker(id));
    }

    @PostMapping
    public ResponseEntity<Gebruiker> create(@RequestBody Gebruiker gebruiker) {
        Gebruiker created = gebruikerService.createGebruiker(gebruiker);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
