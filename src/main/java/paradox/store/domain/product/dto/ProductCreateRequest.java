package paradox.store.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductCreateRequest(
    @NotBlank
    @Size(max = 255)
    String name,
    @Size(max = 500)
    String description,
    String sumnailUrl,
    @NotNull
    Boolean isStackable
) {
}
