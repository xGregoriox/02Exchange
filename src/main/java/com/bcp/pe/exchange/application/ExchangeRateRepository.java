package com.bcp.pe.exchange.application;

import com.bcp.pe.exchange.domain.ExchangeRate;
import com.bcp.pe.exchange.infraestructure.model.bean.CurrencyExchange;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {
    Optional<ExchangeRate> create(ExchangeRate exchangeRate);
    Optional<ExchangeRate> update(Integer id, ExchangeRate exchangeRate);
    void delete(Integer id);
    Optional<ExchangeRate> findById(Integer id);
    List<ExchangeRate> findAll();
    Optional<ExchangeRate> findByCurrencyOriginAndDestination(ExchangeRate exchangeRate);
    Optional<CurrencyExchange> calculateExchangeRate(CurrencyExchange currencyExchange);
}
