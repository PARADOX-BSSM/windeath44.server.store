package paradox.store.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.product.domain.Currency;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.domain.ProductPricePolicy;
import paradox.store.domain.product.domain.key.ProductPricePolicyId;
import paradox.store.domain.product.dto.ProductPricePolicyCreateRequest;
import paradox.store.domain.product.dto.ProductPricePolicyUpdateRequest;
import paradox.store.domain.product.exception.AlreadyExistsPricePolicyException;
import paradox.store.domain.product.exception.NotFoundCurrencyException;
import paradox.store.domain.product.exception.NotFoundPricePolicyException;
import paradox.store.domain.product.exception.NotFoundProductException;
import paradox.store.domain.product.mapper.ProductPricePolicyMapper;
import paradox.store.domain.product.repository.CurrencyRepository;
import paradox.store.domain.product.repository.ProductPricePolicyRepository;
import paradox.store.domain.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductPricePolicyCommandService {

  private final ProductPricePolicyRepository policyRepository;
  private final ProductQueryService productQueryService;
  private final CurrencyQueryService currencyQueryService;
  private final ProductPricePolicyMapper productPricePolicyMapper;

  @Transactional
  public void createPolicy(Long productId, ProductPricePolicyCreateRequest request) {
    Product product = productQueryService.findById(productId);
    Currency currency = currencyQueryService.findById(request.currencyId());

    ProductPricePolicyId id = ProductPricePolicyId.create(productId, request.currencyId());
    boolean exists = policyRepository.existsById(id);
    if (exists) throw AlreadyExistsPricePolicyException.getInstance();

    ProductPricePolicy policy = productPricePolicyMapper.toPolicy(product, currency, request);
    policyRepository.save(policy);
  }

  @Transactional
  public void updatePolicy(Long productId, Long currencyId, ProductPricePolicyUpdateRequest request) {
    ProductPricePolicyId id = ProductPricePolicyId.create(productId, currencyId);
    ProductPricePolicy policy = policyRepository.findById(id)
        .orElseThrow(NotFoundPricePolicyException::getInstance);
    policy.updatePriceAmount(request.priceAmount());
  }

  @Transactional
  public void deletePolicy(Long productId, Long currencyId) {
    ProductPricePolicyId id = ProductPricePolicyId.create(productId, currencyId);
    ProductPricePolicy policy = policyRepository.findById(id)
              .orElseThrow(NotFoundPricePolicyException::getInstance);
    policyRepository.delete(policy);
  }
}
