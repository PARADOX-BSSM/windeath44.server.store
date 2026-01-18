package paradox.store.domain.inventory.exception;

import paradox.store.global.error.exception.ErrorCode;
import paradox.store.global.error.exception.GlobalException;

public class NotFoundInventoryException extends GlobalException {

    public NotFoundInventoryException() {
        super(ErrorCode.INVENTORY_NOT_FOUND);
    }

    public static class Holder {
        private static final NotFoundInventoryException INSTANCE = new NotFoundInventoryException();
    }

    public static NotFoundInventoryException getInstance() {
        return Holder.INSTANCE;
    }
}
