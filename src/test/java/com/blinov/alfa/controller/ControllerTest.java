package com.blinov.alfa.controller;

import com.blinov.alfa.service.GifServiceImplementation;
import com.blinov.alfa.service.OpenExchangeServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OpenExchangeServiceImplementation exchangeRatesService;
    @MockBean
    private GifServiceImplementation gifService;
    @Test
    public void downloadGif() throws Exception {
        mockMvc.perform(get("/check/USD"))
                .andExpect(status().isOk());
    }
    @Test
    public void ifCheckRatesReturn1() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result","rich");
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(exchangeRatesService.checkRates(anyString()))
                .thenReturn(1);
        Mockito.when(gifService.loadGif("rich"))
                .thenReturn(responseEntity);
        mockMvc.perform(get("/check/USD"))
                .andExpect(jsonPath("$.result").value("rich"));
    }

    @Test
    public void ifCheckRatesReturnMinus1() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result","broke");
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(exchangeRatesService.checkRates(anyString()))
                .thenReturn(-1);
        Mockito.when(gifService.loadGif("broke"))
                .thenReturn(responseEntity);
        mockMvc.perform(get("/check/USD"))
                .andExpect(jsonPath("$.result").value("broke"));
    }

    @Test
    public void ifCheckRatesReturn0() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("result","zero");
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        Mockito.when(exchangeRatesService.checkRates(anyString()))
                .thenReturn(0);
        Mockito.when(gifService.loadGif("zero"))
                .thenReturn(responseEntity);
        mockMvc.perform(get("/check/USD"))
                .andExpect(jsonPath("$.result").value("zero"));
    }


}