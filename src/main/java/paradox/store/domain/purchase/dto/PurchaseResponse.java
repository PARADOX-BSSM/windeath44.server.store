package paradox.store.domain.purchase.dto;

import paradox.store.domain.purchase.model.Purchase;

import java.time.LocalDateTime;

public record PurchaseResponse(
    Long purchaseId,
    String userId,
    Long productId,
    String productName,
    Long quantity,
    Long totalPrice,
    LocalDateTime createdAt
) {
    public static PurchaseResponse from(Purchase purchase) {
        return new PurchaseResponse(
                purchase.getPurchaseId(),
                purchase.getUserId(),
                purchase.getProduct().getProductId(),
                purchase.getProduct().getName(),
                purchase.getQuantity(),
                purchase.getTotalPrice(),
                purchase.getCreatedAt()
        );
    }
}
