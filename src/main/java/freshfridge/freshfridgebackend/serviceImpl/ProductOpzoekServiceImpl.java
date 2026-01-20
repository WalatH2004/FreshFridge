package freshfridge.freshfridgebackend.serviceImpl;

import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.integration.openfoodfacts.OpenFoodFactsClient;
import freshfridge.freshfridgebackend.integration.openfoodfacts.dto.OpenFoodFactsProduct;
import freshfridge.freshfridgebackend.integration.openfoodfacts.dto.OpenFoodFactsProductResponse;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import freshfridge.freshfridgebackend.service.ProductOpzoekService;
import org.springframework.stereotype.Service;

@Service
public class ProductOpzoekServiceImpl implements ProductOpzoekService {

    private final ProductRepository productRepository;
    private final OpenFoodFactsClient offClient;

    public ProductOpzoekServiceImpl(ProductRepository productRepository,
                                    OpenFoodFactsClient offClient) {
        this.productRepository = productRepository;
        this.offClient = offClient;
    }

    @Override
    public Product getOrCreateByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseGet(() -> fetchAndStoreFromOpenFoodFacts(barcode));
    }

    private Product fetchAndStoreFromOpenFoodFacts(String barcode) {
        OpenFoodFactsProductResponse offResp = offClient.getProductByBarcode(barcode);
        OpenFoodFactsProduct offProduct = offResp.getProduct();

        String name = offProduct.getProductName();
        String categories = offProduct.getCategories();

        if (categories == null || categories.isBlank()) categories = "Unknown";
        if (name == null || name.isBlank()) name = "Unknown product";

        Product product = new Product();
        product.setNaam(name);
        product.setBarcode(barcode);
        product.setCategorie(categories);
        product.setImageUrl(offProduct.getImageUrl());

        return productRepository.save(product);
    }
}