package paradox.store.domain.product.mapper;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import paradox.store.domain.product.domain.Currency;
import paradox.store.domain.product.dto.CurrencyCreateRequest;
import paradox.store.domain.product.dto.CurrencyResponse;

import java.util.List;

@Component
public class CurrencyMapper {

    public static CurrencyResponse toResponse(Currency currency) {
        return CurrencyResponse.from(currency);
    }

    public Currency toCurrency(CurrencyCreateRequest request) {
        return Currency.of(request.code());
    }

    public List<CurrencyResponse> toCursorResponse(Slice<Currency> currencies) {
        return currencies.getContent()
                .stream()
                .map(CurrencyResponse::from)
                .toList();
    }

}
