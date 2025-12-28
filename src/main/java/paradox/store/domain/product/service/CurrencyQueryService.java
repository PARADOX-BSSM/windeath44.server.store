package paradox.store.domain.product.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import paradox.store.domain.product.domain.Currency;
import paradox.store.domain.product.dto.CurrencyResponse;
import paradox.store.domain.product.exception.NotFoundCurrencyException;
import paradox.store.domain.product.mapper.CurrencyMapper;
import paradox.store.domain.product.repository.CurrencyRepository;
import paradox.store.global.dto.CursorPage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyQueryService {

  private final CurrencyRepository currencyRepository;
  private final CurrencyMapper currencyMapper;

    public Currency findById(Long currencyId) {
        return currencyRepository.findById(currencyId)
                .orElseThrow(NotFoundCurrencyException::getInstance);
    }

    public CurrencyResponse getCurrency(Long currencyId) {
    Currency currency = currencyRepository.findById(currencyId)
        .orElseThrow(NotFoundCurrencyException::getInstance);
    return CurrencyMapper.toResponse(currency);
  }

  public CursorPage<CurrencyResponse> getCurrencies(Long cursor, int size) {
    int limit = Math.max(1, size);
    Pageable pageable = PageRequest.of(0, limit, Sort.by("currencyId").ascending());

    Slice<Currency> currencies = (cursor == null)
        ? currencyRepository.findAllByOrderByCurrencyIdAsc(pageable)
        : currencyRepository.findByCurrencyIdGreaterThanOrderByCurrencyIdAsc(cursor, pageable);

    boolean hasNext = currencies.hasNext();
    List<CurrencyResponse> responses = currencyMapper.toCursorResponse(currencies);

    return new CursorPage<>(responses, hasNext);
  }
}
