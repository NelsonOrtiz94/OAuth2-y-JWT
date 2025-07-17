package com.lta.security.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRestApi {

    @GetMapping
    public Map<String, Object> dataTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        return Map.of();
    }
}
