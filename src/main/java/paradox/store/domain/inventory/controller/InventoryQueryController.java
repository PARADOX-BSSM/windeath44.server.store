package paradox.store.domain.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paradox.store.domain.inventory.dto.InventoryWithItemsResponse;
import paradox.store.domain.inventory.service.InventoryQueryService;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/inventory")
@RequiredArgsConstructor
public class InventoryQueryController {

    private final InventoryQueryService inventoryQueryService;

    @GetMapping
    public ResponseEntity<ResponseDto<InventoryWithItemsResponse>> getInventoryWithItems(
            @RequestHeader("user-id") String userId,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        InventoryWithItemsResponse response = inventoryQueryService.getInventoryWithItems(userId, cursor, size);
        ResponseDto<InventoryWithItemsResponse> responseDto =
                HttpUtil.success("successfully fetched inventory with items", response);
        return ResponseEntity.ok(responseDto);
    }
}
