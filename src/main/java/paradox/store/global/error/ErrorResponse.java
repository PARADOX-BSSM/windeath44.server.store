package paradox.store.global.error;

import com.example.user.global.error.exception.ErrorCode;

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
