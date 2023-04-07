package com.bcp.pe.exchange.infraestructure.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "tbl_exchange_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateDao {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La moneda origen no puede ser vacia")
    private String originCurrency;

    @NotNull(message = "La moneda destino no puede ser vacia")
    private String destinationCurrency;

    @Positive (message = "El valor del tipo de cambio debe ser mayor que cero")
    private BigDecimal amount;
}
