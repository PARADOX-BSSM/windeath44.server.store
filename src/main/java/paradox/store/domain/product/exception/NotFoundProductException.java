package paradox.store.domain.product.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class NotFoundProductException extends GlobalException {

  public NotFoundProductException() {
    super(ErrorCode.PRODUCT_NOT_FOUND);
  }

  public static class Holder {
      private static final NotFoundProductException INSTANCE = new NotFoundProductException();
  }

  public static NotFoundProductException getInstance() {
      return Holder.INSTANCE;
  }
}
