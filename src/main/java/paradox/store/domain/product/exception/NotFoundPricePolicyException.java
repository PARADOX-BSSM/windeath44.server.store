package paradox.store.domain.product.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class NotFoundPricePolicyException extends GlobalException {

  public NotFoundPricePolicyException() {
    super(ErrorCode.PRICE_POLICY_NOT_FOUND);
  }

  public static class Holder {
    private static final NotFoundPricePolicyException INSTANCE = new NotFoundPricePolicyException();
  }

  public static NotFoundPricePolicyException getInstance() {
    return Holder.INSTANCE;
  }
}
