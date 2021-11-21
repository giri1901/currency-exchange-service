package com.gr.microservice.CurrencyExchangeService.rest.controller;

import com.gr.microservice.CurrencyExchangeService.repo.CurrencyExchangeRepository;
import com.gr.microservice.CurrencyExchangeService.rest.bean.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchangeInfo(@NonNull @PathVariable String from, @NonNull @PathVariable String to) {
        /*CurrencyExchange currencyExchange = new CurrencyExchange(10001, "USD", "INR", 65.00, "8000 Instance Id");
        String serverPort = environment.getProperty("server.port");
        currencyExchange.setEnvironment(serverPort);
        return currencyExchange;*/
        logger.info("Excahnge COntroller called with from: {}, to: {}", from, to);
        CurrencyExchange result = repository.findByFromAndTo(from, to);
        if(Objects.isNull(result))
            throw new RuntimeException("Currency Exchange data is not available from " + from + " , to: " + to);

        String port = environment.getProperty("local.server.port");
        result.setEnvironment(port);
        return result;

    }
}
