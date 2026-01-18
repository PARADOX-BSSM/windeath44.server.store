package paradox.store.global.error;

import paradox.store.global.error.exception.ErrorCode;

public record ErrorResponse(
        int status,
        String message
) {
  public ErrorResponse(ErrorCode errorCode) {
    this(
            errorCode.getStatus(),
            errorCode.getMessage()
    );
  }
}
