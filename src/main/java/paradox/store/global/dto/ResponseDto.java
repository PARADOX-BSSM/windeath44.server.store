package paradox.store.global.dto;

public record ResponseDto<T> (
        String message,
        T data
) {
}
