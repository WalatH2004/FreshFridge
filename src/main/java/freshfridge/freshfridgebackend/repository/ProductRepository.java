package freshfridge.freshfridgebackend.repository;

import freshfridge.freshfridgebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
