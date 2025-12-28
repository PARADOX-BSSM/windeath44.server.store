package paradox.store.domain.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import paradox.store.domain.product.dto.CurrencyCreateRequest;
import paradox.store.domain.product.dto.CurrencyUpdateRequest;
import paradox.store.domain.product.service.CurrencyCommandService;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/currencies")
@RequiredArgsConstructor
public class CurrencyCommandController {

  private final CurrencyCommandService currencyCommandService;

  @PostMapping
  public ResponseEntity<ResponseDto<Void>> createCurrency(
      @Valid @RequestBody CurrencyCreateRequest request
  ) {
    currencyCommandService.createCurrency(request);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully created currency");
    return ResponseEntity.ok(responseDto);
  }

  @PatchMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<Void>> updateCurrency(
      @PathVariable("currencyId") Long currencyId,
      @Valid @RequestBody CurrencyUpdateRequest request
  ) {
    currencyCommandService.updateCurrency(currencyId, request);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully updated currency");
    return ResponseEntity.ok(responseDto);
  }

  @DeleteMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<Void>> deleteCurrency(
      @PathVariable("currencyId") Long currencyId
  ) {
    currencyCommandService.deleteCurrency(currencyId);
    ResponseDto<Void> responseDto = HttpUtil.success("successfully deleted currency");
    return ResponseEntity.ok(responseDto);
  }
}
