package paradox.store.domain.inventory.dto;

import paradox.store.domain.inventory.model.Item;
import paradox.store.domain.product.dto.ProductResponse;

import java.time.LocalDateTime;

public record ItemResponse(
    Long itemId,
    ProductResponse product,
    Long amount,
    LocalDateTime acquiredAt
) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(
            item.getItemId(),
            ProductResponse.from(item.getProduct()),
            item.getAmount(),
            item.getAcquiredAt()
        );
    }
}
