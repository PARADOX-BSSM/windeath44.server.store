package paradox.store.global.dto;

import java.util.List;

public record OffsetPage<T> (
        List<T> values,
        int total
) {
    public static <T> OffsetPage<T> of(List<T> content, int total) {
        return new OffsetPage<>(content, total);
    }
}
