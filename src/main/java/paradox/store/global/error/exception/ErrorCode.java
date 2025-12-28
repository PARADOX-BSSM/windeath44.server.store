package paradox.store.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  PRODUCT_NOT_FOUND(404, "product not found"),
  CURRENCY_ALREADY_EXISTS(409, "currency already exists"),
  CURRENCY_NOT_FOUND(404, "currency not found"),
  PRICE_POLICY_ALREADY_EXISTS(409, "price policy already exists"),
  PRICE_POLICY_NOT_FOUND(404, "price policy not found");
  private int status;
  private String message;
}
