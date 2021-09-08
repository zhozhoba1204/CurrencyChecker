package com.blinov.alfa.service;

import com.blinov.alfa.entity.OpenExchangeRates;
import com.blinov.alfa.feignclient.OpenExchangeRatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class OpenExchangeServiceImplementation implements OpenExchangeService{

    private OpenExchangeRatesClient openExchangeRatesClient;

    @Value("${key_openexchangerates}")
    private String key;
    @Value("${base_openexchangerates}")
    private String base;

    @Autowired
    public OpenExchangeServiceImplementation(OpenExchangeRatesClient openExchangeRatesClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
    }


    public OpenExchangeRates getLatest(){
        return openExchangeRatesClient.getLatest(key);
    }

    public OpenExchangeRates getHistorical(){
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return openExchangeRatesClient.getHistorical(yesterday,key);
    }

    public BigDecimal calculate(Map<String, Double> map, String currency){
        Double baseRate = map.get(base);
        Double findRate = map.get(currency);
        BigDecimal result = new BigDecimal(findRate/baseRate);
        return result;
    }
    @Override
    public int checkRates(String currency) {
        OpenExchangeRates latest = getLatest();
        OpenExchangeRates historical = getHistorical();
        Map<String, Double> latestMap = latest.getRates();
        Map<String, Double> historicalMap = historical.getRates();
        BigDecimal latestResult = calculate(latestMap, currency);
        BigDecimal historicalResult = calculate(historicalMap, currency);
        if (historicalResult.compareTo(latestResult)>0){
            return 1;
        }
        else if (historicalResult.compareTo(latestResult)<0)
            return -1;
        else return 0;
    }
}
