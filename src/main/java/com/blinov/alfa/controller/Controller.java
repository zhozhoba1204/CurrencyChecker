package com.blinov.alfa.controller;


import com.blinov.alfa.service.GifService;
import com.blinov.alfa.service.OpenExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class Controller {
    private OpenExchangeService openExchangeService;
    private GifService gifService;

    @Autowired
    public Controller(OpenExchangeService openExchangeService, GifService gifService) {
        this.openExchangeService = openExchangeService;
        this.gifService = gifService;
    }


    @GetMapping(value = "/check/{currency}")
    public ResponseEntity<Map> downloadGif(@PathVariable String currency) {
        if (openExchangeService.checkRates(currency) > 0) {
            return gifService.loadGif("rich");
        } else if (openExchangeService.checkRates(currency) < 0)
            return gifService.loadGif("broke");
        else return gifService.loadGif("zero");
    }
}
