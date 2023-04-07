package com.bcp.pe.exchange.infraestructure.model.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyExchange {
    private BigDecimal amount;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal exchangeRate;
    private BigDecimal amountWithExchangeRate;
}
