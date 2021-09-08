package com.blinov.alfa.service;

import com.blinov.alfa.feignclient.GifClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.blinov")
class GifServiceImplementationTest {
    @Autowired
    private GifServiceImplementation gifServiceImplementation;
    @MockBean
    private GifClient gifClient;

    @Test
    public void loadGif() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap(), HttpStatus.OK);
        Mockito.when(gifClient.getRandom(anyString(), anyString()))
                .thenReturn(testEntity);
        ResponseEntity<Map> result = gifServiceImplementation.loadGif("happy");
        assertEquals(testEntity, result);
    }

}