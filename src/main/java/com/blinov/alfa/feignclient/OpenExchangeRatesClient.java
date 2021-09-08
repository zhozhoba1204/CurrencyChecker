package com.blinov.alfa.feignclient;

import com.blinov.alfa.entity.OpenExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openex", url = "${url_openexchangerates}")
public interface OpenExchangeRatesClient {

    @GetMapping("/latest.json")
    OpenExchangeRates getLatest(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{yesterday}.json")
    OpenExchangeRates getHistorical(@PathVariable String yesterday, @RequestParam("app_id") String appId);
}
