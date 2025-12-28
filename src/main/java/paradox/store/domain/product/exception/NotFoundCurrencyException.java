package paradox.store.domain.product.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class NotFoundCurrencyException extends GlobalException {

  public NotFoundCurrencyException() {
    super(ErrorCode.CURRENCY_NOT_FOUND);
  }

  public static class Holder {
    private static final NotFoundCurrencyException INSTANCE = new NotFoundCurrencyException();
  }

  public static NotFoundCurrencyException getInstance() {
    return Holder.INSTANCE;
  }
}
