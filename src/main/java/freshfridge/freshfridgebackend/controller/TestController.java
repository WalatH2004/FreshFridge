package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final ProductRepository productRepository;

    public TestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/test-products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
