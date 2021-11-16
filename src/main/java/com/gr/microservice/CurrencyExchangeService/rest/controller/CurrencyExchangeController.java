package com.gr.microservice.CurrencyExchangeService.rest.controller;

import com.gr.microservice.CurrencyExchangeService.repo.CurrencyExchangeRepository;
import com.gr.microservice.CurrencyExchangeService.rest.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public List<CurrencyExchange> getCurrencyExchangeInfo(@NonNull @PathVariable String from, @NonNull @PathVariable String to) {
        /*CurrencyExchange currencyExchange = new CurrencyExchange(10001, "USD", "INR", 65.00, "8000 Instance Id");
        String serverPort = environment.getProperty("server.port");
        currencyExchange.setEnvironment(serverPort);
        return currencyExchange;*/
        List<CurrencyExchange> result = repository.findByFromAndTo(from, to);
        if(Objects.isNull(result) || result.isEmpty())
            throw new RuntimeException("Currency Exchange data is not available from " + from + " , to: " + to);
        return result;

    }
}
