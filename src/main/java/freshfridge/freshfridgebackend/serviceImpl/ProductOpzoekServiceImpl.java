package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import freshfridge.freshfridgebackend.service.ProductOpzoekService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProductOpzoekServiceImpl implements ProductOpzoekService {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public ProductOpzoekServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getOrCreateByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseGet(() -> fetchAndStoreFromOpenFoodFacts(barcode));
    }

    private Product fetchAndStoreFromOpenFoodFacts(String barcode) {
        String url = "https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json";

        Map<?, ?> response = restTemplate.getForObject(url, Map.class);
        if (response == null) {
            throw new RuntimeException("Geen response van OpenFoodFacts");
        }

        Object statusObj = response.get("status");
        int status = (statusObj instanceof Number) ? ((Number) statusObj).intValue() : 0;
        if (status != 1) {
            throw new RuntimeException("Product niet gevonden voor barcode: " + barcode);
        }

        Map<?, ?> productMap = (Map<?, ?>) response.get("product");

        String name = safeString(productMap, "product_name");
        String categories = safeString(productMap, "categories");

        if (categories == null || categories.isBlank()) categories = "Unknown";
        if (name == null || name.isBlank()) name = "Unknown product";

        Product product = new Product();
        product.setNaam(name);
        product.setBarcode(barcode);
        product.setCategorie(categories);

        return productRepository.save(product);
    }

    private String safeString(Map<?, ?> map, String key) {
        if (map == null) return null;
        Object v = map.get(key);
        return v == null ? null : v.toString();
    }
}
