package paradox.store.domain.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPricePolicyCreateRequest(
    @NotNull Long currencyId,
    @NotNull Long priceAmount
) {
}
