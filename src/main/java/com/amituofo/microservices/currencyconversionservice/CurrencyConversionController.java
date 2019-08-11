package com.amituofo.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CurrencyConversionController {

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
      @PathVariable BigDecimal quantity) {

    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);
    ResponseEntity<CurrencyConversionBean> responseEntity =
        new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/USD/to/RMB",
            CurrencyConversionBean.class, uriVariables);
    CurrencyConversionBean bean = responseEntity.getBody();
    CurrencyConversionBean currencyConversionBean = CurrencyConversionBean.builder()
        .id(bean.getId())
        .from(from)
        .to(to)
        .conversionMultiple(bean.getConversionMultiple())
        .quantity(quantity)
        .totalCalculatedAmount(quantity.multiply(bean.getConversionMultiple()))
        .port(bean.getPort())
        .build();
    return currencyConversionBean;
  }
}
