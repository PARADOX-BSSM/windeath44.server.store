package paradox.store.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CurrencyCreateRequest(
    @NotBlank
    @Size(max = 50)
    String code
) {
}
