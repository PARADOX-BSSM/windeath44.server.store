package paradox.store.domain.product.dto;

import paradox.store.domain.product.model.Currency;

public record CurrencyResponse(
    Long currencyId,
    String code
) {

  public static CurrencyResponse from(Currency currency) {
    return new CurrencyResponse(currency.getCurrencyId(), currency.getCode());
  }
}
