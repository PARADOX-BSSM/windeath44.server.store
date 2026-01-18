package paradox.store.domain.purchase.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class InsufficientMoneyException extends GlobalException {

    private static final InsufficientMoneyException INSTANCE = new InsufficientMoneyException();

    private InsufficientMoneyException() {
        super(ErrorCode.INSUFFICIENT_MONEY);
    }

    public static InsufficientMoneyException getInstance() {
        return INSTANCE;
    }
}
