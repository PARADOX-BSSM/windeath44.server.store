package paradox.store.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paradox.store.domain.product.dto.CurrencyResponse;
import paradox.store.domain.product.service.CurrencyQueryService;
import paradox.store.global.dto.CursorPage;
import paradox.store.global.dto.ResponseDto;
import paradox.store.global.util.HttpUtil;

@RestController
@RequestMapping("/store/currencies")
@RequiredArgsConstructor
public class CurrencyQueryController {

  private final CurrencyQueryService currencyQueryService;

  @GetMapping("/{currencyId}")
  public ResponseEntity<ResponseDto<CurrencyResponse>> getCurrency(
      @PathVariable("currencyId") Long currencyId
  ) {
    CurrencyResponse response = currencyQueryService.getCurrency(currencyId);
    ResponseDto<CurrencyResponse> responseDto = HttpUtil.success("successfully fetched currency", response);
    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<ResponseDto<CursorPage<CurrencyResponse>>> getCurrencies(
      @RequestParam(value = "cursor", required = false) Long cursor,
      @RequestParam(value = "size", defaultValue = "20") int size
  ) {
    CursorPage<CurrencyResponse> response = currencyQueryService.getCurrencies(cursor, size);
    ResponseDto<CursorPage<CurrencyResponse>> responseDto =
        HttpUtil.success("successfully fetched currencies", response);
    return ResponseEntity.ok(responseDto);
  }
}
