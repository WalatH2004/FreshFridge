package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Scanner;
import freshfridge.freshfridgebackend.repository.ScannerRepository;
import freshfridge.freshfridgebackend.service.ScannerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScannerServiceImpl implements ScannerService {

    private final ScannerRepository scannerRepository;

    public ScannerServiceImpl(ScannerRepository scannerRepository) {
        this.scannerRepository = scannerRepository;
    }

    @Override
    public Scanner addScanner(Scanner scanner) {
        return scannerRepository.save(scanner);
    }

    @Override
    public Scanner getScanner(Integer id) {
        return scannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Scanner niet gevonden"));
    }

    @Override
    public List<Scanner> getAllScanners() {
        return scannerRepository.findAll();
    }

    @Override
    public void deleteScanner(Integer id) {
        if (!scannerRepository.existsById(id)) {
            throw new EntityNotFoundException("Scanner niet gevonden");
        }
        scannerRepository.deleteById(id);
    }
}
