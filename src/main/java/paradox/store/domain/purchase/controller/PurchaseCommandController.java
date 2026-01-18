package paradox.store.domain.purchase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import paradox.store.domain.purchase.dto.PurchaseRequest;
import paradox.store.domain.purchase.service.PurchaseCommandService;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseCommandController {

    private final PurchaseCommandService purchaseCommandService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> purchase(@Valid @RequestBody PurchaseRequest request) {
        purchaseCommandService.purchase(request);
        return ResponseEntity.ok(HttpUtil.success("purchase completed"));
    }
}
