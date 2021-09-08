package com.blinov.alfa.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "gif", url = "${url_giphy}")
public interface GifClient {

    @GetMapping("/random")
    ResponseEntity<Map> getRandom(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
