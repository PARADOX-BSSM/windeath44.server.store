package paradox.store.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paradox.store.domain.product.dto.ProductPricePolicyCreateRequest;
import paradox.store.domain.product.dto.ProductPricePolicyUpdateRequest;
import paradox.store.domain.product.service.ProductPricePolicyCommandService;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/products/{productId}/price-policies")
@RequiredArgsConstructor
public class ProductPricePolicyCommandController {

  private final ProductPricePolicyCommandService productPricePolicyCommandService;

  @PostMapping
  public ResponseEntity<ResponseDto<Void>> createPolicy(
      @PathVariable("productId") Long productId,
      @Valid @RequestBody ProductPricePolicyCreateRequest request
  ) {
    productPricePolicyCommandService.createPolicy(productId, request);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully created price policy");
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<Void>> updatePolicy(
      @PathVariable("productId") Long productId,
      @PathVariable("currencyId") Long currencyId,
      @Valid @RequestBody ProductPricePolicyUpdateRequest request
  ) {
    productPricePolicyCommandService.updatePolicy(productId, currencyId, request);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully updated price policy");
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<Void>> deletePolicy(
      @PathVariable("productId") Long productId,
      @PathVariable("currencyId") Long currencyId
  ) {
    productPricePolicyCommandService.deletePolicy(productId, currencyId);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully deleted price policy");
    return ResponseEntity.ok(responseDto);
  }
}
