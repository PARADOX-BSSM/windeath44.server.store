package paradox.store.domain.purchase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paradox.store.domain.purchase.dto.PurchaseResponse;
import paradox.store.domain.purchase.service.PurchaseQueryService;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseQueryController {

    private final PurchaseQueryService purchaseQueryService;

    /**
     * Get Purchase History
     */

    @GetMapping
    public ResponseEntity<ResponseDto<List<PurchaseResponse>>> getPurchases(
            @RequestParam String userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "20") int size
    ) {
        List<PurchaseResponse> purchases = purchaseQueryService.getPurchasesByUserId(userId, cursor, size);
        return ResponseEntity.ok(HttpUtil.success("success", purchases));
    }
}
