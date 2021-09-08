package com.blinov.alfa.service;

import com.blinov.alfa.feignclient.GifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifServiceImplementation implements GifService {
    private GifClient gifClient;

    @Value("${key_giphy}")
    private String key;

    @Autowired
    public GifServiceImplementation(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @Override
    public ResponseEntity<Map> loadGif(String tag){
        return gifClient.getRandom(key, tag);
    }
}
