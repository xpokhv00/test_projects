package com.example.fibonacci_api_gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FibonacciGatewayController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/fibonacci")
    public String getFibonacci(@RequestParam(value = "n", defaultValue = "10") int n) {
        String fibonacciServiceUrl = "http://localhost:8081/fibonacci?n=" + n;
        return restTemplate.getForObject(fibonacciServiceUrl, String.class);
    }
}