package paradox.store.domain.product.mapper;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import paradox.store.domain.product.domain.Product;
import paradox.store.domain.product.dto.ProductCreateRequest;
import paradox.store.domain.product.dto.ProductResponse;

import java.util.List;

@Component
public class ProductMapper {

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.from(product);
    }

    public Product toProduct(ProductCreateRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .sumnailUrl(request.sumnailUrl())
                .isStackable(request.isStackable())
                .build();
    }

    public List<ProductResponse> toCursorResponse(Slice<Product> products) {
        return products.getContent()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }
}
