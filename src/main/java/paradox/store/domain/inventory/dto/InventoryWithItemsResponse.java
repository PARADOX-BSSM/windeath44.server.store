package paradox.store.domain.inventory.dto;

import paradox.store.domain.inventory.model.Inventory;
import paradox.store.global.dto.CursorPage;

public record InventoryWithItemsResponse(
    Long inventoryId,
    String userId,
    CursorPage<ItemResponse> items
) {
    public static InventoryWithItemsResponse of(Inventory inventory, CursorPage<ItemResponse> items) {
        return new InventoryWithItemsResponse(
            inventory.getInventoryId(),
            inventory.getUserId(),
            items
        );
    }
}
