package paradox.store.domain.product.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import paradox.store.domain.product.model.ProductPricePolicy;
import paradox.store.domain.product.model.key.ProductPricePolicyId;

public interface ProductPricePolicyRepository extends JpaRepository<ProductPricePolicy, ProductPricePolicyId> {
  Slice<ProductPricePolicy> findByIdProductIdOrderByIdCurrencyIdAsc(Long productId, Pageable pageable);
  Slice<ProductPricePolicy> findByIdProductIdAndIdCurrencyIdGreaterThanOrderByIdCurrencyIdAsc(
      Long productId,
      Long currencyId,
      Pageable pageable
  );
}
