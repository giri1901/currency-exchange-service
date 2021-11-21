package com.gr.microservice.CurrencyExchangeService.rest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "defaultResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "defaultResponse")
    public String sampleApi(){
        logger.info("Request for /sample-api is called");
        ResponseEntity<String> restTemplate = new RestTemplate().getForEntity("http://localhost:8080/same-dummy-url", String.class);

        return restTemplate.getBody();
        //return "Sample API";
    }

    public String defaultResponse(Exception ex) {
        return "Fallback Response. error was:" + ex.getMessage();
    }
}
