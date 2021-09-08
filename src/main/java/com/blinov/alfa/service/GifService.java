package com.blinov.alfa.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface GifService {
    ResponseEntity<Map> loadGif(String tag);
}
