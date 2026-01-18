package paradox.store.domain.inventory.mapper;

import org.springframework.stereotype.Component;
import paradox.store.domain.inventory.dto.ItemResponse;
import paradox.store.domain.inventory.model.Item;

import java.util.List;

@Component
public class ItemMapper {


    public List<ItemResponse> toResponseList(List<Item> items) {
        return items.stream()
                .map(this::toResponse)
                .toList();
    }

    private ItemResponse toResponse(Item item) {
        return ItemResponse.from(item);
    }
}
