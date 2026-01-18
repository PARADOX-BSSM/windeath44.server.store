package paradox.store.domain.product.dto;

import paradox.store.domain.product.model.Product;

public record ProductResponse(
    Long productId,
    String name,
    String description,
    String sumnailUrl,
    Boolean isStackable,
    Long money
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getSumnailUrl(),
                product.getIsStackable(),
                product.getMoney()
        );
    }
}
