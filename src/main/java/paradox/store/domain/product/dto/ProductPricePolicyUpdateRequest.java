package paradox.store.domain.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPricePolicyUpdateRequest(
    @NotNull Long priceAmount
) {
}
