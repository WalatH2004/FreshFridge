package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Scanner;
import java.util.List;

public interface ScannerService {

    Scanner addScanner(Scanner scanner);

    Scanner getScanner(Integer id);

    List<Scanner> getAllScanners();

    void deleteScanner(Integer id);
}
