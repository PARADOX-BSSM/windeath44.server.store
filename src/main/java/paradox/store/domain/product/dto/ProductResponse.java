package paradox.store.domain.product.dto;

import paradox.store.domain.product.domain.Product;

public record ProductResponse(
    Long productId,
    String name,
    String description,
    String sumnailUrl,
    Boolean isStackable
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getSumnailUrl(),
                product.getIsStackable()
        );
    }
}
