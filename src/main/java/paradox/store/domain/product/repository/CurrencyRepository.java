package paradox.store.domain.product.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import paradox.store.domain.product.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
  boolean existsByCode(String code);
  boolean existsByCodeAndCurrencyIdNot(String code, Long currencyId);
  Slice<Currency> findByCurrencyIdGreaterThanOrderByCurrencyIdAsc(Long currencyId, Pageable pageable);
  Slice<Currency> findAllByOrderByCurrencyIdAsc(Pageable pageable);
}
