package paradox.store.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paradox.store.domain.product.dto.ProductPricePolicyResponse;
import paradox.store.domain.product.service.ProductPricePolicyQueryService;
import paradox.store.global.dto.CursorPage;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/products/{productId}/price-policies")
@RequiredArgsConstructor
public class ProductPricePolicyQueryController {

  private final ProductPricePolicyQueryService productPricePolicyQueryService;

  @GetMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<ProductPricePolicyResponse>> getPolicy(
      @PathVariable("productId") Long productId,
      @PathVariable("currencyId") Long currencyId
  ) {
    ProductPricePolicyResponse response = productPricePolicyQueryService.getPolicy(productId, currencyId);
    ResponseDto<ProductPricePolicyResponse> responseDto =
        HttpUtil.success("successfully fetched price policy", response);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<ResponseDto<CursorPage<ProductPricePolicyResponse>>> getPolicies(
      @PathVariable("productId") Long productId,
      @RequestParam(value = "cursor", required = false) Long cursor,
      @RequestParam(value = "size", defaultValue = "20") int size
  ) {
    CursorPage<ProductPricePolicyResponse> response =
        productPricePolicyQueryService.getPolicies(productId, cursor, size);
    ResponseDto<CursorPage<ProductPricePolicyResponse>> responseDto =
        HttpUtil.success("successfully fetched price policies", response);
    return ResponseEntity.ok(responseDto);
  }
}
