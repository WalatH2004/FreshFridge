package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Scanner;
import freshfridge.freshfridgebackend.service.ScannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scanners")
public class ScannerController {

    private final ScannerService scannerService;

    public ScannerController(ScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping
    public ResponseEntity<Scanner> addScanner(@RequestBody Scanner scanner) {
        Scanner saved = scannerService.addScanner(scanner);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scanner> getScanner(@PathVariable Integer id) {
        Scanner scanner = scannerService.getScanner(id);
        return ResponseEntity.ok(scanner);
    }

    @GetMapping
    public ResponseEntity<List<Scanner>> getAllScanners() {
        List<Scanner> scanners = scannerService.getAllScanners();
        return ResponseEntity.ok(scanners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScanner(@PathVariable Integer id) {
        scannerService.deleteScanner(id);
        return ResponseEntity.noContent().build();
    }
}
