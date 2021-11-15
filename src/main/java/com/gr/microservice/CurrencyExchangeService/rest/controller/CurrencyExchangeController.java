package com.gr.microservice.CurrencyExchangeService.rest.controller;

import com.gr.microservice.CurrencyExchangeService.rest.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @GetMapping("/currency-exchange/from/USD/to/INR")
    public CurrencyExchange getCurrencyExchangeInfo() {
        CurrencyExchange currencyExchange = new CurrencyExchange(10001, "USD", "INR", 65.00, "8000 Instance Id");
        String serverPort = environment.getProperty("server.port");
        currencyExchange.setEnvironment(serverPort);
        return currencyExchange;

    }
}
