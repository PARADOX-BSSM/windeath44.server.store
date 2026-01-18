package paradox.store.domain.purchase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
    @NotBlank
    String userId,
    @NotNull
    Long productId,
    @NotNull
    @Min(1)
    Long quantity
) {
}
