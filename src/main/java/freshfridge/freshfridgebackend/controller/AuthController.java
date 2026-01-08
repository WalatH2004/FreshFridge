package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.dto.LoginRequest;
import freshfridge.freshfridgebackend.dto.LoginResponse;
import freshfridge.freshfridgebackend.entity.Gebruiker;
import freshfridge.freshfridgebackend.repository.GebruikerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(GebruikerRepository gebruikerRepository,
                          PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        Gebruiker gebruiker = gebruikerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Ongeldig email of wachtwoord"));

        if (!passwordEncoder.matches(request.getPassword(), gebruiker.getPassword())) {
            throw new RuntimeException("Ongeldig email of wachtwoord");
        }

        LoginResponse response = new LoginResponse(
                gebruiker.getGebruikernr(),
                gebruiker.getEmail(),
                gebruiker.getVoornaam()
        );

        return ResponseEntity.ok(response);
    }
}
