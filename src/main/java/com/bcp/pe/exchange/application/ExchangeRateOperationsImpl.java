package com.bcp.pe.exchange.application;

import com.bcp.pe.exchange.domain.ExchangeRate;
import com.bcp.pe.exchange.infraestructure.model.bean.CurrencyExchange;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateOperationsImpl implements ExchangeRateOperations{
    private final ExchangeRateRepository repository;

    public ExchangeRateOperationsImpl(final ExchangeRateRepository exchangeRateRepository){
        repository = exchangeRateRepository;
    }
    @Override
    public Optional<ExchangeRate> create(ExchangeRate exchangeRate) {
        return repository.create(exchangeRate);
    }

    @Override
    public Optional<ExchangeRate> update(Integer id, ExchangeRate exchangeRate) {
        return repository.update(id,exchangeRate);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Override
    public Optional<ExchangeRate> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyOriginAndDestination(ExchangeRate exchangeRate) {
        return repository.findByCurrencyOriginAndDestination(exchangeRate);
    }

    @Override
    public Optional<CurrencyExchange> calculateExchangeRate(CurrencyExchange currencyExchange) {
        return repository
                .findByCurrencyOriginAndDestination(mapCurrencyToExchangeRate(currencyExchange))
                .map(p -> calculateAmount(p,currencyExchange));
    }

    private ExchangeRate mapCurrencyToExchangeRate(CurrencyExchange currencyExchange){
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setOriginCurrency(currencyExchange.getOriginCurrency());
        exchangeRate.setDestinationCurrency(currencyExchange.getDestinationCurrency());
        return exchangeRate;
    }
    private CurrencyExchange calculateAmount(ExchangeRate exchangeRate,CurrencyExchange currencyExchange ){
        currencyExchange.setExchangeRate(exchangeRate.getAmount());
        currencyExchange.setAmountWithExchangeRate(currencyExchange.getAmount().multiply(exchangeRate.getAmount()));
        return currencyExchange;
    }
}
