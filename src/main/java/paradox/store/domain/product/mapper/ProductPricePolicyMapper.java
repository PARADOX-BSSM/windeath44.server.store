package paradox.store.domain.product.mapper;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import paradox.store.domain.product.domain.Currency;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.domain.ProductPricePolicy;
import paradox.store.domain.product.domain.key.ProductPricePolicyId;
import paradox.store.domain.product.dto.ProductPricePolicyCreateRequest;
import paradox.store.domain.product.dto.ProductPricePolicyResponse;

import java.util.List;

@Component
public class ProductPricePolicyMapper {

    public static ProductPricePolicyResponse toResponse(ProductPricePolicy policy) {
        return ProductPricePolicyResponse.from(policy);
    }

    public ProductPricePolicy toPolicy(Product product, Currency currency, ProductPricePolicyCreateRequest request) {
        return ProductPricePolicy.builder()
                .id(ProductPricePolicyId.create(product.getProductId(), currency.getCurrencyId()))
                .currency(currency)
                .product(product)
                .priceAmount(request.priceAmount())
                .build();
    }

    public List<ProductPricePolicyResponse> toCursorResponse(Slice<ProductPricePolicy> policies) {
        return policies.getContent()
                .stream()
                .map(ProductPricePolicyResponse::from)
                .toList();
    }
}
