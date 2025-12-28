package paradox.store.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paradox.store.domain.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
