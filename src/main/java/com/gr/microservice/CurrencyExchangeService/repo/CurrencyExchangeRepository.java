package com.gr.microservice.CurrencyExchangeService.repo;

import com.gr.microservice.CurrencyExchangeService.rest.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    //@Query("from CurrencyExchange C where C.from_currency=:from and C.to_currency=:to")
    CurrencyExchange findByFromAndTo(String from, String to);
}
