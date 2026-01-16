package freshfridge.freshfridgebackend.service;

import freshfridge.freshfridgebackend.entity.Product;

public interface ProductOpzoekService {
    Product getOrCreateByBarcode(String barcode);
}
