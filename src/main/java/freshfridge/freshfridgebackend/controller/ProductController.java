package freshfridge.freshfridgebackend.controller;

import freshfridge.freshfridgebackend.entity.Product;
import freshfridge.freshfridgebackend.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producten")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
