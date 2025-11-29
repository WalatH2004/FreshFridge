package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.repository.GebruikerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gebruiker")
public class GebruikerController {

    private final GebruikerRepository gebruikerRepository;

    public GebruikerController(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @GetMapping
    public List<Gebruiker> getAll() {
        return gebruikerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gebruiker> getById(@PathVariable Integer id) {
        return gebruikerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gebruiker create(@RequestBody Gebruiker gebruiker) {
        return gebruikerRepository.save(gebruiker);
    }
}
