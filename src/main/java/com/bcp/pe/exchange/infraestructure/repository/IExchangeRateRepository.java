package com.bcp.pe.exchange.infraestructure.repository;

import com.bcp.pe.exchange.infraestructure.model.dao.ExchangeRateDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IExchangeRateRepository extends JpaRepository<ExchangeRateDao,Integer> {

    public Optional<ExchangeRateDao> findByOriginCurrencyAndDestinationCurrency(String originCurrency, String destinationCurrency);
}
