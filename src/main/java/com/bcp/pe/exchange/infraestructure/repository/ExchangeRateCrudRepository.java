package com.bcp.pe.exchange.infraestructure.repository;

import com.bcp.pe.exchange.application.ExchangeRateRepository;
import com.bcp.pe.exchange.domain.ExchangeRate;
import com.bcp.pe.exchange.infraestructure.model.bean.CurrencyExchange;
import com.bcp.pe.exchange.infraestructure.model.dao.ExchangeRateDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExchangeRateCrudRepository implements ExchangeRateRepository {
    private final IExchangeRateRepository repository;

    public ExchangeRateCrudRepository(IExchangeRateRepository iExchangeRateRepository){
        repository = iExchangeRateRepository;
    }
    @Override
    public Optional<ExchangeRate> create(ExchangeRate exchangeRate) {
        Optional<ExchangeRateDao> exchangeRateDao = Optional.of(repository.save(mapExchangeRateToExchangeRateDao(exchangeRate)));
        return exchangeRateDao.map(this::mapExchangeRateDaoToExchangeRate);
    }

    @Override
    public Optional<ExchangeRate> update(Integer id, ExchangeRate exchangeRate) {
       return repository.findById(exchangeRate.getId())
               .flatMap(p -> create(mapExchangetRateDaoIdToExchangeRateId(p,exchangeRate)));
    }

    @Override
    public void delete(Integer id) {
        //repository.findById(id).flatMap(p -> repository.delete(p));

    }

    @Override
    public Optional<ExchangeRate> findById(Integer id) {
        return repository.findById(id).map(this::mapExchangeRateDaoToExchangeRate);
    }

    @Override
    public List<ExchangeRate> findAll() {
        return repository.findAll().stream().map(this::mapExchangeRateDaoToExchangeRate).collect(Collectors.toList());
    }

    @Override
    public Optional<ExchangeRate> findByCurrencyOriginAndDestination(ExchangeRate exchangeRate) {
        return repository.findByOriginCurrencyAndDestinationCurrency(exchangeRate.getOriginCurrency(), exchangeRate.getDestinationCurrency())
                .map(this::mapExchangeRateDaoToExchangeRate);
    }

    @Override
    public Optional<CurrencyExchange> calculateExchangeRate(CurrencyExchange currencyExchange) {
        return repository.findByOriginCurrencyAndDestinationCurrency(currencyExchange.getOriginCurrency(), currencyExchange.getDestinationCurrency())
                .map(p -> calculateAmount(mapExchangeRateDaoToExchangeRate(p),currencyExchange));
    }
    private CurrencyExchange calculateAmount(ExchangeRate exchangeRate,CurrencyExchange currencyExchange ){
        currencyExchange.setExchangeRate(exchangeRate.getAmount());
        currencyExchange.setAmountWithExchangeRate(currencyExchange.getAmount().multiply(exchangeRate.getAmount()));
        return currencyExchange;
    }
    private ExchangeRateDao mapExchangeRateToExchangeRateDao(ExchangeRate exchangeRate){
        ExchangeRateDao exchangeRateDao = new ExchangeRateDao();
        BeanUtils.copyProperties(exchangeRate,exchangeRateDao);
        return exchangeRateDao;
    }
    private ExchangeRate mapExchangeRateDaoToExchangeRate(ExchangeRateDao exchangeRateDao){
        ExchangeRate exchangeRate = new ExchangeRate();
        BeanUtils.copyProperties(exchangeRateDao,exchangeRate);
        return exchangeRate;
    }

    private ExchangeRate mapExchangetRateDaoIdToExchangeRateId(ExchangeRateDao exchangeRateDao,ExchangeRate exchangeRate ){
        exchangeRate.setId(exchangeRateDao.getId());
        return exchangeRate;
    }
}
