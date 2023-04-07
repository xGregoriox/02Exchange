package com.bcp.pe.exchange.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ExchangeRate {
    private Integer id;
    private String originCurrency;
    private String destinationCurrency;
    private BigDecimal amount;
}
