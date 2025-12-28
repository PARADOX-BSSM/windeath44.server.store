package paradox.store.domain.inventory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.inventory.dto.InventoryWithItemsResponse;
import paradox.store.domain.inventory.dto.ItemResponse;
import paradox.store.domain.inventory.exception.NotFoundInventoryException;
import paradox.store.domain.inventory.mapper.InventoryMapper;
import paradox.store.domain.inventory.mapper.ItemMapper;
import paradox.store.domain.inventory.model.Inventory;
import paradox.store.domain.inventory.model.Item;
import paradox.store.domain.inventory.repository.InventoryRepository;
import paradox.store.domain.inventory.repository.ItemRepository;
import paradox.store.global.dto.CursorPage;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryQueryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final InventoryMapper inventoryMapper;
    private final ItemMapper itemMapper;

    public InventoryWithItemsResponse getInventoryWithItems(String userId, Long cursor, int size) {
        Inventory inventory = inventoryRepository.findByUserId(userId)
                .orElseThrow(NotFoundInventoryException::getInstance);

        int limit = Math.max(1, size);
        Pageable pageable = PageRequest.of(0, limit);

        Slice<Long> itemIdSlice = (cursor == null)
                ? itemRepository.findItemIdsByInventoryId(inventory.getInventoryId(), pageable)
                : itemRepository.findItemIdsByInventoryIdAndCursor(inventory.getInventoryId(), cursor, pageable);

        boolean hasNext = itemIdSlice.hasNext();
        List<Long> itemIds = itemIdSlice.getContent();

        List<Item> items = itemIds.isEmpty()
                ? List.of()
                : itemRepository.findAllByItemIdsWithProduct(itemIds);

        List<ItemResponse> itemResponses = itemMapper.toResponseList(items);
        CursorPage<ItemResponse> itemPage = new CursorPage<>(itemResponses, hasNext);
        return inventoryMapper.toResponse(inventory, itemPage);
    }
}
