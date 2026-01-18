package paradox.store.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  PRODUCT_NOT_FOUND(404, "product not found"),
  INVENTORY_NOT_FOUND(404, "inventory not found"),
  INSUFFICIENT_MONEY(400, "insufficient money");

  private int status;
  private String message;
}
