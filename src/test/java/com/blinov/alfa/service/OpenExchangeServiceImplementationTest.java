package com.blinov.alfa.service;

import com.blinov.alfa.entity.OpenExchangeRates;
import com.blinov.alfa.feignclient.OpenExchangeRatesClient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.blinov")
class OpenExchangeServiceImplementationTest {

    OpenExchangeRates openExchangeRates;
    OpenExchangeRates openExchangeRatesHistorical;

    @Autowired
    OpenExchangeServiceImplementation openExchangeServiceImplementation;

    @MockBean
    OpenExchangeRatesClient openExchangeRatesClient;

    @BeforeEach
    public void init(){
        openExchangeRates = new OpenExchangeRates("disclaimer", "licence",2,"base", new HashMap<>());
        openExchangeRatesHistorical = new OpenExchangeRates("disclaimer", "licence",1,"base", new HashMap<>());

    }

    @Test
    void getLatest() {
        Mockito.when(openExchangeRatesClient.getLatest(anyString())).
                thenReturn(openExchangeRates);
        OpenExchangeRates result = openExchangeServiceImplementation.getLatest();
        Assert.assertEquals(openExchangeRates, result);
    }

    @Test
    void getHistorical() {
        Mockito.when(openExchangeRatesClient.getHistorical(anyString(), anyString())).
                thenReturn(openExchangeRatesHistorical);
        OpenExchangeRates result = openExchangeServiceImplementation.getHistorical();
        Assert.assertEquals(openExchangeRatesHistorical, result);
    }

}