package com.bcp.pe.exchange.infraestructure.rest;

import com.bcp.pe.exchange.application.ExchangeRateOperations;
import com.bcp.pe.exchange.domain.ExchangeRate;
import com.bcp.pe.exchange.infraestructure.model.bean.CurrencyExchange;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/service/exchange")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateOperations exchangeRateOperations;

    @GetMapping
    public Single<ResponseEntity<List<ExchangeRate>>> getAll(){
        return Single.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(exchangeRateOperations.findAll())
        );
    }
    @PostMapping
    public Single<ResponseEntity<Optional<CurrencyExchange>>> calculateExchangeRate(@RequestBody CurrencyExchange currencyExchange){
        return Single.just(
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(exchangeRateOperations.calculateExchangeRate(currencyExchange))
        );
    }

}
