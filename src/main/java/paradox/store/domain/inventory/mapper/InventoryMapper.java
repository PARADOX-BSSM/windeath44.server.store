package paradox.store.domain.inventory.mapper;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import paradox.store.domain.inventory.dto.InventoryWithItemsResponse;
import paradox.store.domain.inventory.dto.ItemResponse;
import paradox.store.domain.inventory.model.Inventory;
import paradox.store.domain.inventory.model.Item;
import paradox.store.global.dto.CursorPage;

import java.util.List;

@Component
public class InventoryMapper {

    public static InventoryWithItemsResponse toResponse(Inventory inventory, CursorPage<ItemResponse> itemPage) {
        return InventoryWithItemsResponse.of(inventory, itemPage);
    }

    public List<ItemResponse> toCursorResponse(Slice<Item> items) {
        return items.getContent()
                .stream()
                .map(ItemResponse::from)
                .toList();
    }
}
