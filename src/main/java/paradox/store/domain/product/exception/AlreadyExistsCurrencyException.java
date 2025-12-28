package paradox.store.domain.product.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class AlreadyExistsCurrencyException extends GlobalException {

  public AlreadyExistsCurrencyException() {
    super(ErrorCode.CURRENCY_ALREADY_EXISTS);
  }

  public static class Holder {
    private static final AlreadyExistsCurrencyException INSTANCE = new AlreadyExistsCurrencyException();
  }

  public static AlreadyExistsCurrencyException getInstance() {
    return Holder.INSTANCE;
  }
}
