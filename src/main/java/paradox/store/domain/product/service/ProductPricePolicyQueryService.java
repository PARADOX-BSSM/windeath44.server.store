package paradox.store.domain.product.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paradox.store.domain.product.model.ProductPricePolicy;
import paradox.store.domain.product.model.key.ProductPricePolicyId;
import paradox.store.domain.product.dto.ProductPricePolicyResponse;
import paradox.store.domain.product.exception.NotFoundPricePolicyException;
import paradox.store.domain.product.mapper.ProductPricePolicyMapper;
import paradox.store.domain.product.repository.ProductPricePolicyRepository;
import paradox.store.global.dto.CursorPage;

@Service
@RequiredArgsConstructor
public class ProductPricePolicyQueryService {

  private final ProductPricePolicyRepository policyRepository;
  private final ProductPricePolicyMapper productPricePolicyMapper;

  public ProductPricePolicyResponse getPolicy(Long productId, Long currencyId) {
    ProductPricePolicyId id = ProductPricePolicyId.create(productId, currencyId);
    ProductPricePolicy policy = policyRepository.findById(id)
        .orElseThrow(NotFoundPricePolicyException::getInstance);
    return ProductPricePolicyMapper.toResponse(policy);
  }

  public CursorPage<ProductPricePolicyResponse> getPolicies(Long productId, Long cursor, int size) {
    int limit = Math.max(1, size);
    Pageable pageable = PageRequest.of(0, limit + 1, Sort.by("id.currencyId").ascending());

    Slice<ProductPricePolicy> policies = (cursor == null)
        ? policyRepository.findByIdProductIdOrderByIdCurrencyIdAsc(productId, pageable)
        : policyRepository.findByIdProductIdAndIdCurrencyIdGreaterThanOrderByIdCurrencyIdAsc(
            productId, cursor, pageable);

    boolean hasNext = policies.hasNext();
    List<ProductPricePolicyResponse> responses = productPricePolicyMapper.toCursorResponse(policies);

    return new CursorPage<>(responses, hasNext);
  }
}
