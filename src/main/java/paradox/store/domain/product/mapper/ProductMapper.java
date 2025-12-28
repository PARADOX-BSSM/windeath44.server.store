package paradox.store.domain.product.mapper;

import org.springframework.stereotype.Component;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.dto.ProductCreateRequest;

@Component
public class ProductMapper {

    public Product toProduct(ProductCreateRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .sumnailUrl(request.sumnailUrl())
                .isStackable(request.isStackable())
                .build();
    }
}
