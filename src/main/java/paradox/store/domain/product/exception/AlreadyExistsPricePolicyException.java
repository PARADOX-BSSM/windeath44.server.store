package paradox.store.domain.product.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class AlreadyExistsPricePolicyException extends GlobalException {

  public AlreadyExistsPricePolicyException() {
    super(ErrorCode.PRICE_POLICY_ALREADY_EXISTS);
  }

  public static class Holder {
    private static final AlreadyExistsPricePolicyException INSTANCE = new AlreadyExistsPricePolicyException();
  }

  public static AlreadyExistsPricePolicyException getInstance() {
    return Holder.INSTANCE;
  }
}
