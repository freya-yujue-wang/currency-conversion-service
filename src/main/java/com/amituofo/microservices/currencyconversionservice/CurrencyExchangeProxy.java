package com.amituofo.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {
  @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
  CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
