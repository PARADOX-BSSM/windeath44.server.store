package paradox.store.domain.product.repository;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import paradox.store.domain.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Slice<Product> findByProductIdGreaterThanOrderByProductIdAsc(Long productId, Pageable pageable);
  Slice<Product> findAllByOrderByProductIdAsc(Pageable pageable);
}
