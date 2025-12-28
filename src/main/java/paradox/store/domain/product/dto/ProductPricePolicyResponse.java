package paradox.store.domain.product.dto;

import paradox.store.domain.product.domain.ProductPricePolicy;

public record ProductPricePolicyResponse(
    Long productId,
    Long currencyId,
    Long priceAmount
) {

  public static ProductPricePolicyResponse from(ProductPricePolicy policy) {
    return new ProductPricePolicyResponse(
        policy.getProductId(),
        policy.getCurrencyId(),
        policy.getPriceAmount()
    );
  }
}
