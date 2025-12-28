package paradox.store.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paradox.store.domain.product.domain.Currency;
import paradox.store.domain.product.dto.CurrencyCreateRequest;
import paradox.store.domain.product.dto.CurrencyUpdateRequest;
import paradox.store.domain.product.exception.AlreadyExistsCurrencyException;
import paradox.store.domain.product.exception.NotFoundCurrencyException;
import paradox.store.domain.product.mapper.CurrencyMapper;
import paradox.store.domain.product.repository.CurrencyRepository;

@Service
@RequiredArgsConstructor
public class CurrencyCommandService {

  private final CurrencyRepository currencyRepository;
  private final CurrencyMapper currencyMapper;

  @Transactional
  public void createCurrency(CurrencyCreateRequest request) {
      boolean exists = currencyRepository.existsByCode(request.code());
      if (exists) throw AlreadyExistsCurrencyException.getInstance();

      Currency currency = currencyMapper.toCurrency(request);
      currencyRepository.save(currency);
  }

  @Transactional
  public void updateCurrency(Long currencyId, CurrencyUpdateRequest request) {
    Currency currency = currencyRepository.findById(currencyId)
        .orElseThrow(NotFoundCurrencyException::getInstance);
    boolean exists = currencyRepository.existsByCodeAndCurrencyIdNot(request.code(), currencyId);

    if (exists) throw AlreadyExistsCurrencyException.getInstance();
    currency.updateCode(request.code());
  }

  @Transactional
  public void deleteCurrency(Long currencyId) {
    Currency currency = currencyRepository.findById(currencyId)
                    .orElseThrow(NotFoundCurrencyException::getInstance);
    currencyRepository.delete(currency);
  }
}
